package com.example.someturrets.datagen;

import com.example.someturrets.Someturrets;
import com.example.someturrets.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemModels extends ItemModelProvider {

    public TutItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Someturrets.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.BEDROCK_REINFORCED_STEEL_BLOCK_ITEM.get().getRegistryName().getPath(), modLoc("block/bedrock_reinforced_steel_block"));

        singleTexture(Registration.DARK_STEEL_INGOT.get().getRegistryName().getPath(),
        mcLoc("item/generated"),
        "layer0", modLoc("item/dark_steel_ingot"));
    }
}

