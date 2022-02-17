package us.dison.rarityctl;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.util.Formatting;
import us.dison.rarityctl.config.Config;
import us.dison.rarityctl.config.RarityCustom;

import java.util.List;

public class EarlyRiser implements Runnable {
    private static final MappingResolver REMAPPER = FabricLoader.getInstance().getMappingResolver();
    private static final String CLASS_FORMATTING = REMAPPER.mapClassName("intermediary", "net.minecraft.class_124");
    private static final String CLASS_RARITY = REMAPPER.mapClassName("intermediary", "net.minecraft.class_1814");

    @Override
    public void run() {
        System.out.println("EARLY RISER");

        Config.init();
        final Config config = Config.getInstance();

        final List<RarityCustom> rarities = config.getConfig().getRarities();
        for (int i = 0; i < rarities.size(); i++) {
            final RarityCustom rarity = rarities.get(i);
            final int newIndex = 128 + i;
            final char newCode = (char) newIndex;
            final String newFormat = "RARITYCONTROL"+i;

            ClassTinkerers.enumBuilder(CLASS_FORMATTING, String.class, char.class, boolean.class, int.class, Integer.class).addEnum(newFormat,newFormat, newCode, false, newIndex, rarity.getColor()).build();

            switch (rarity.getName()) {
                case "COMMON"   -> ClassTinkerers.enumBuilder(CLASS_RARITY, "L"+CLASS_FORMATTING+";").addEnum("COMMMON", () -> new Object[]{ClassTinkerers.getEnum(Formatting.class, newFormat)}).build();
                case "UNCOMMON"   -> ClassTinkerers.enumBuilder(CLASS_RARITY, "L"+CLASS_FORMATTING+";").addEnum("UNCOMMON", () -> new Object[]{ClassTinkerers.getEnum(Formatting.class, newFormat)}).build();
                case "RARE"   -> ClassTinkerers.enumBuilder(CLASS_RARITY, "L"+CLASS_FORMATTING+";").addEnum("RARE", () -> new Object[]{ClassTinkerers.getEnum(Formatting.class, newFormat)}).build();
                case "EPIC"   -> ClassTinkerers.enumBuilder(CLASS_RARITY, "L"+CLASS_FORMATTING+";").addEnum("EPIC", () -> new Object[]{ClassTinkerers.getEnum(Formatting.class, newFormat)}).build();
                default -> ClassTinkerers.enumBuilder(CLASS_RARITY, "L"+CLASS_FORMATTING+";").addEnum(rarity.getName(), () -> new Object[]{ClassTinkerers.getEnum(Formatting.class, newFormat)}).build();
            }
        }

    }
}
