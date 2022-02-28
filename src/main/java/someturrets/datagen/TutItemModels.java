package someturrets.datagen;

import someturrets.Someturrets;
import someturrets.setup.Registration;
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
        withExistingParent(Registration.BEDROCK_STEEL_STABILIZED_NETWORK_CABLE_ITEM.get().getRegistryName().getPath(), modLoc("block/bedrock_steel_stabilized_network_cable"));
        withExistingParent(Registration.DEAD_MATTER_ITEM.get().getRegistryName().getPath(), modLoc("block/dead_matter"));

        withExistingParent(Registration.POWERGEN_ITEM.get().getRegistryName().getPath(), modLoc("block/powergen/main"));

        withExistingParent(Registration.SCREEN_ITEM.get().getRegistryName().getPath(), modLoc("block/screen/main"));


        singleTexture(Registration.BEDROCK_REINFORCED_STEEL_INGOT.get().getRegistryName().getPath(),
        mcLoc("item/generated"),
        "layer0", modLoc("item/bedrock_reinforced_steel_ingot"));
        singleTexture(Registration.QUANTUM_ENERGY_CELL.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/quantum_energy_cell"));
        singleTexture(Registration.MASTER_SECURITY_CARD.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/master_security_card"));
        singleTexture(Registration.SECURITY_CARD.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/security_card"));
        singleTexture(Registration.SECURITY_CARD_MODIFICATION_PIECE.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/security_card_modification_piece"));
    }
}

