package someturrets.datagen;

import net.minecraft.client.Minecraft;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import someturrets.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

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
                .unlockedBy("has Security Card", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SECURITY_CARD.get(), Registration.SECURITY_CARD_MODIFICATION_PIECE.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(Registration.SCREEN.get())
                .pattern("bbb")
                .pattern("pqp")
                .pattern("bbb")
                .define('b', Registration.BEDROCK_REINFORCED_STEEL_INGOT.get())
                .define('p', Tags.Items.GLASS_PANES)
                .define('q', Registration.QUANTUM_ENERGY_CELL.get())
                .unlockedBy("has QEC", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BEDROCK_REINFORCED_STEEL_INGOT.get(), Registration.QUANTUM_ENERGY_CELL.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(Registration.BEDROCK_REINFORCED_STEEL_INGOT.get(), 9)
                .requires(Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get())
                .unlockedBy("has BDSB", has(Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get())
                .pattern("bbb")
                .pattern("bbb")
                .pattern("bbb")
                .define('b', Registration.BEDROCK_REINFORCED_STEEL_INGOT.get())
                .unlockedBy("has BRSI", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BEDROCK_REINFORCED_STEEL_INGOT.get()))
                .save(consumer);
    }
}
