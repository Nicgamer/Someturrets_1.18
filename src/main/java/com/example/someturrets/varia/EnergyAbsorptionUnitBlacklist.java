package com.example.someturrets.varia;

import com.example.someturrets.setup.Registration;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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