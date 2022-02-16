package us.dison.rarityctl;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RarityControl implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {

        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();



        for (int i = 0; i < Formatting.values().length; i++) {
            Formatting formatting = Formatting.values()[i];
            System.out.println(formatting.name());
            System.out.println(formatting.getColorValue());
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        for (int i = 0; i < Rarity.values().length; i++) {
            Rarity rarity = Rarity.values()[i];
            System.out.println(rarity.name());
            System.out.println(rarity.formatting.getColorValue());
            System.out.println("");
        }
//        try {
//            Rarity rarity = ClassTinkerers.getEnum(Rarity.class, "CUSTOM");
//            System.out.println(rarity.name());
//            System.out.println(rarity.formatting.getColorValue());
//            System.out.println("");
//
//            System.out.println("Rarity: " + rarity + " with ordinal " + rarity.ordinal());
//
//            for (Rarity r : Rarity.values()) {
//                System.out.println(r.ordinal() + " => " + r);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
