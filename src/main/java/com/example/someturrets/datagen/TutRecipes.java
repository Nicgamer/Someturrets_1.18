package com.example.someturrets.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

import static com.example.someturrets.setup.Registration.BEDROCK_REINFORCED_STEEL_BLOCK;

public class TutRecipes extends RecipeProvider {

    public TutRecipes(DataGenerator generatorIn) {super(generatorIn);}

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(BEDROCK_REINFORCED_STEEL_BLOCK.get())
                .pattern("ogo")
                .pattern("gdg")
                .pattern("ogo")
                .define('o', Items.OBSIDIAN)
                .define('g', Items.GLASS)
                .define('d', Items.DIAMOND_BLOCK)
                .unlockedBy("OBSIDIAN", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OBSIDIAN, Items.GLASS, Items.DIAMOND_BLOCK))
                .save(consumer);


    }
}
