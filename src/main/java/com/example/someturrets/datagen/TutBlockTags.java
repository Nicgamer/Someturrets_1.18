package com.example.someturrets.datagen;

import com.example.someturrets.Someturrets;
import com.example.someturrets.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockTags extends BlockTagsProvider {

    public TutBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Someturrets.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get());
    }

    @Override
    public String getName() {return "Somestuff Tags"; }
}
