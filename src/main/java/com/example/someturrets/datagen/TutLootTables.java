package com.example.someturrets.datagen;

import com.example.someturrets.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.LootTables;

public class TutLootTables extends BaseLootTableProvider {

    public TutLootTables(DataGenerator dataGeneratorIn) {super(dataGeneratorIn);}

    @Override
    protected void addTables() {
        lootTables.put(Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get(), createSimpleTable("Bedrock Reinforced Steel Block", Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get()));

    }



}
