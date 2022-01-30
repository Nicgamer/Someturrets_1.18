package com.example.someturrets.setup;

import com.example.someturrets.blocks.PowergenBE;
import com.example.someturrets.blocks.PowergenBlock;
import com.example.someturrets.blocks.PowergenContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.example.someturrets.Someturrets.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        CONTAINERS.register(bus);

    }

    //Some common properties for our blocks and items
    public static final BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(-1);
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);

    public static final RegistryObject<Block> BEDROCK_REINFORCED_STEEL_BLOCK = BLOCKS.register( "bedrock_reinforced_steel_block", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> BEDROCK_REINFORCED_STEEL_BLOCK_ITEM = fromBlock(BEDROCK_REINFORCED_STEEL_BLOCK);
    public static final RegistryObject<Block> BEDROCK_STEEL_STABILIZED_NETWORK_CABLE = BLOCKS.register( "bedrock_steel_stabilized_network_cable", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> BEDROCK_STEEL_STABILIZED_NETWORK_CABLE_ITEM = fromBlock(BEDROCK_STEEL_STABILIZED_NETWORK_CABLE);

    public static final RegistryObject<PowergenBlock> POWERGEN = BLOCKS.register("powergen", PowergenBlock::new);
    public static final RegistryObject<Item> POWERGEN_ITEM = fromBlock(POWERGEN);
    public static final RegistryObject<BlockEntityType<PowergenBE>> POWERGEN_BE = BLOCK_ENTITIES.register("powergen", () -> BlockEntityType.Builder.of(PowergenBE::new, POWERGEN.get()).build(null));
    public static final RegistryObject<MenuType<PowergenContainer>> POWERGEN_CONTAINER = CONTAINERS.register("powergen",
            () -> IForgeMenuType.create((windowId, inv, data) -> new PowergenContainer(windowId, data.readBlockPos(), inv, inv.player)));

    public static final RegistryObject<Item> DARK_STEEL_INGOT = ITEMS.register( "dark_steel_ingot", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> QUANTUM_ENERGY_CELL = ITEMS.register( "quantum_energy_cell", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SECURITY_CARD = ITEMS.register( "security_card", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> MASTER_SECURITY_CARD = ITEMS.register( "master_security_card", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SECURITY_CARD_MODIFICATION_PIECE = ITEMS.register( "security_card_modification_piece", () -> new Item(ITEM_PROPERTIES));



    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
