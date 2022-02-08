package someturrets.datagen;

import someturrets.setup.Registration;
import net.minecraft.data.DataGenerator;

public class TutLootTables extends BaseLootTableProvider {

    public TutLootTables(DataGenerator dataGeneratorIn) {super(dataGeneratorIn);}

    @Override
    protected void addTables() {
        lootTables.put(Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get(), createSimpleTable("bedrock_reinforced_steel_block.png", Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get()));
        lootTables.put(Registration.BEDROCK_STEEL_STABILIZED_NETWORK_CABLE.get(), createSimpleTable("bedrock_steel_stabilized_network_cable.png", Registration.BEDROCK_STEEL_STABILIZED_NETWORK_CABLE.get()));
        lootTables.put(Registration.POWERGEN.get(), createStandardTable("powergen", Registration.POWERGEN.get(), Registration.POWERGEN_BE.get()));
    }



}
