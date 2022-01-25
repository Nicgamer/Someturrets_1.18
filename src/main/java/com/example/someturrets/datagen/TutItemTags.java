package com.example.someturrets.datagen;

import com.example.someturrets.Someturrets;
import com.example.someturrets.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemTags extends ItemTagsProvider {

    public TutItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, Someturrets.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(Tags.Items.INGOTS)
                .add(Registration.DARK_STEEL_INGOT.get());

    }

    @Override
    public String getName() {return "Somestuff Tags"; }
}
