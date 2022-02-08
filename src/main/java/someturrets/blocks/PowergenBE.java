package someturrets.blocks;

import someturrets.setup.Registration;
import someturrets.varia.CustomEnergyStorage;
import someturrets.varia.EnergyAbsorptionUnitBlacklist;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

public class PowergenBE extends BlockEntity {

    public static final int POWERGEN_CAPACITY = 200000; //Max capacity
    public static final int POWERGEN_GENERATE = 1000; //Generation per tick
    public static final int POWERGEN_SEND = 3000; // Power to send out per tick
    public static final int INPUT_SLOTS = 6;
    public static final int OUTPUT_SLOTS = 1;

    private boolean generating = false;
    private Block generatingBlock;
    private boolean actuallyGenerating = false;

    private int generatingCounter = 0;

    //Never create lazy optional in getCapability. Always place them in the tile entity:
    private final ItemStackHandler inputItems = createInputItemHandler();
    private final LazyOptional<IItemHandler> inputItemHandler = LazyOptional.of(() -> inputItems);
    private final ItemStackHandler outputItems = createOutputItemHandler();
    private final LazyOptional<IItemHandler> outputItemHandler = LazyOptional.of(() -> outputItems);

    private final CustomEnergyStorage energyStorage = createEnergy();
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);


    private int counter;

    public PowergenBE(BlockPos pos, BlockState state) {
        super(Registration.POWERGEN_BE.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        inputItemHandler.invalidate();
        outputItemHandler.invalidate();
        energy.invalidate();
    }

    public void setGeneratingBlock() {
        // Only accepts Dead Matter as an output result
        Block generatingBlock = Registration.DEAD_MATTER.get();
        this.generatingBlock = generatingBlock;
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);

    }

    public void tickServer() {
        if (counter > 0) {
            setChanged();
        }

        BlockState blockState = level.getBlockState(worldPosition);
        if (blockState.getValue(BlockStateProperties.POWERED) != counter > 0) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, counter > 0),
                    Block.UPDATE_ALL);
        }

        sendOutPower();

        boolean areWeGenerating = false;
        if (generating) {
            areWeGenerating = generateDeadMatter();
        }
        if (areWeGenerating != actuallyGenerating) {
            actuallyGenerating = areWeGenerating;
            setChanged();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    private void sendOutPower() {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                BlockEntity be = level.getBlockEntity(worldPosition.relative(direction));
                if (be != null) {
                    boolean doContinue = be.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveEnergy(Math.min(capacity.get(), POWERGEN_SEND), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
                                    setChanged();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("Inventory")) {
            inputItems.deserializeNBT(tag.getCompound("Inventory"));
        }
        if (tag.contains("Inventory")) {
            outputItems.deserializeNBT(tag.getCompound("Inventory"));
        }
        if (tag.contains("Energy")) {
            energyStorage.deserializeNBT(tag.get("Energy"));
        }
        if (tag.contains("Info")) {
            counter = tag.getCompound("Info").getInt("Counter");
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("Inventory", inputItems.serializeNBT());
        tag.put("Inventory", outputItems.serializeNBT());
        tag.put("Energy", energyStorage.serializeNBT());

        CompoundTag infoTag = new CompoundTag();
        infoTag.putInt("Counter", counter);
        tag.put("Info", infoTag);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                //To make sure the TE persists when the chunk is saved later we need to
                //mark dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return EnergyAbsorptionUnitBlacklist.EnergyAbsorptionUnitBlacklist(stack) >= 0;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (EnergyAbsorptionUnitBlacklist.EnergyAbsorptionUnitBlacklist(stack) < 0) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private boolean generateDeadMatter() {
        // The player didn't select anything to generate
        if (generatingBlock == null) {
            return false;
        }
        boolean areWeGenerating = false;
        for (int i = 0; i < inputItems.getSlots(); i++) {
            ItemStack item = inputItems.getStackInSlot(i);
            if (!item.isEmpty()) {
                energyStorage.addEnergy(POWERGEN_GENERATE * 6);
                // The API documentation from getStackInSlot says you are not allowed to modify the itemstack returned
                // by getStackInSlot. That's why we make a copy here
                item = item.copy();
                item.shrink(6);
                // Put back the item with one less (can be empty)
                inputItems.setStackInSlot(i, item);
                generatingCounter++;
                areWeGenerating = true;
                setChanged();
                if (generatingCounter >= 1) {
                    generatingCounter = 0;
                    // For each of these ores we try to insert it in the output buffer or else throw it on the ground
                    ItemHandlerHelper.insertItem(outputItems, new ItemStack(generatingBlock.asItem()), false);
                }
            }
        }
        return areWeGenerating;
    }


    @Nonnull
    private ItemStackHandler createInputItemHandler() {
        return new ItemStackHandler(INPUT_SLOTS) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Nonnull
    private ItemStackHandler createOutputItemHandler() {
        return new ItemStackHandler(OUTPUT_SLOTS) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(POWERGEN_CAPACITY, 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == Direction.DOWN) {
                return outputItemHandler.cast();
            } else {
                return inputItemHandler.cast();
            }
        } else if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }
}
