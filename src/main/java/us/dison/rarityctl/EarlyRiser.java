package us.dison.rarityctl;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.util.Formatting;

public class EarlyRiser implements Runnable {
    @Override
    public void run() {
        System.out.println("EARLY RISER");
        System.out.println("EARLY RISER");
        System.out.println("EARLY RISER");
        System.out.println("EARLY RISER");
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String formatting = remapper.mapClassName("intermediary", "net.minecraft.class_124");
        ClassTinkerers.enumBuilder(formatting, String.class, char.class, boolean.class, int.class, Integer.class).addEnum("CUSTOMFORMATTING","CUSTOMFORMATTING", 'z', false, 69, 0xFF0000).build();

        String rarity = remapper.mapClassName("intermediary", "net.minecraft.class_1814");
//        ClassTinkerers.enumBuilder(rarity, "Lnet/minecraft/util/Formatting").addEnum("CUSTOMRARITY", ClassTinkerers.getEnum(Formatting.class, "CUSTOMFORMATTING")).build();
        ClassTinkerers.enumBuilder(rarity, "L"+formatting+";").addEnum("CUSTOMRARITY", () -> new Object[]{ClassTinkerers.getEnum(Formatting.class, "CUSTOMFORMATTING")}).build();
    }
}
