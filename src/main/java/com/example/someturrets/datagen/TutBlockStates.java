package com.example.someturrets.datagen;

import com.example.someturrets.Someturrets;
import com.example.someturrets.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockStates extends BlockStateProvider {

    public TutBlockStates(DataGenerator gen, ExistingFileHelper helper) {super(gen, Someturrets.MODID, helper);}

    @Override
    protected void registerStatesAndModels() {

        simpleBlock(Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get());
        simpleBlock(Registration.BEDROCK_STEEL_STABILIZED_NETWORK_CABLE.get());
    }
}
