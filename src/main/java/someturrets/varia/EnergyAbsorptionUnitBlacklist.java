package someturrets.varia;

import someturrets.setup.Registration;
import net.minecraft.world.item.ItemStack;

public class EnergyAbsorptionUnitBlacklist {

    public static int EnergyAbsorptionUnitBlacklist(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else if (stack.getItem().equals(Registration.DEAD_MATTER_ITEM.get()) | stack.getItem().equals(Registration.BEDROCK_REINFORCED_STEEL_BLOCK_ITEM.get()) | stack.getItem().equals(Registration.BEDROCK_STEEL_STABILIZED_NETWORK_CABLE_ITEM.get())) {
            return 0;
        } else {
            return 1;
        }
    }
}