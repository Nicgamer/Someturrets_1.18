package someturrets.datagen;

import someturrets.Someturrets;
import someturrets.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static someturrets.blocks.PowergenBlock.MESSAGE_POWERGEN;
import static someturrets.blocks.PowergenBlock.SCREEN_SOMETURRETS_POWERGEN;
import static someturrets.setup.ModSetup.TAB_NAME;

public class TutLanguageProvider extends LanguageProvider {

    public TutLanguageProvider(DataGenerator gen, String locale) {super(gen, Someturrets.MODID, locale);}

    @Override
    protected void addTranslations() {
        add("itemGroup." + TAB_NAME, "Someturrets");
        add(MESSAGE_POWERGEN, "Power generator generating %s per tick!");
        add(SCREEN_SOMETURRETS_POWERGEN, "Power generator");

        add(Registration.POWERGEN.get(), "Power Generator");

        add(Registration.BEDROCK_REINFORCED_STEEL_BLOCK.get(), "Bedrock Reinforced Steel Block");
        add(Registration.DARK_STEEL_INGOT.get(), "Dark Steel Ingot");
        add(Registration.QUANTUM_ENERGY_CELL.get(), "Quantum Energy Cell");
        add(Registration.BEDROCK_STEEL_STABILIZED_NETWORK_CABLE.get(), "Bedrock Steel Stabilized Network Cable");
        add(Registration.MASTER_SECURITY_CARD.get(), "Master Security Card");
        add(Registration.SECURITY_CARD.get(), "Security Card");
        add(Registration.SECURITY_CARD_MODIFICATION_PIECE.get(), "Security Card Modification Piece");
        add(Registration.DEAD_MATTER.get(), "Dead Matter");
    }
}
