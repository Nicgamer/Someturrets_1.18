package com.example.someturrets.datagen;

import com.example.someturrets.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

import static com.example.someturrets.setup.Registration.BEDROCK_REINFORCED_STEEL_BLOCK;
import static com.example.someturrets.setup.Registration.MASTER_SECURITY_CARD;

public class TutRecipes extends RecipeProvider {

    public TutRecipes(DataGenerator generatorIn) {super(generatorIn);}

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Registration.MASTER_SECURITY_CARD.get())
                .pattern(" m ")
                .pattern("msm")
                .pattern(" m ")
                .define('m', Registration.SECURITY_CARD_MODIFICATION_PIECE.get())
                .define('s', Registration.SECURITY_CARD.get())
                .unlockedBy("SECURITY_CARD", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SECURITY_CARD.get(), Registration.SECURITY_CARD_MODIFICATION_PIECE.get()))
                .save(consumer);
    }
}
