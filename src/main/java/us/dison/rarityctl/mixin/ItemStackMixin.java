package us.dison.rarityctl.mixin;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import us.dison.rarityctl.config.Config;
import us.dison.rarityctl.config.RarityCustom;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    @Shadow public abstract boolean hasEnchantments();

    @Inject(method = "getRarity()Lnet/minecraft/util/Rarity;", at = @At("RETURN"), cancellable = true)
    private void getRarity(CallbackInfoReturnable<Rarity> cir) {
        Rarity unmodifiedRarity = ((ItemAccessor) getItem()).getRarity();
        Rarity originalRarity = unmodifiedRarity;
        if (hasEnchantments()) { // Increase rarity like vanilla does
            switch (unmodifiedRarity) {
                case COMMON, UNCOMMON -> originalRarity = Rarity.RARE;
                case RARE -> originalRarity = Rarity.EPIC;
            }
        }
        Rarity newRarity = null;

        if (Config.getOverrides().contains(originalRarity.name())) { // If the item isn't specified, but its default rarity has been modified,
            newRarity = ClassTinkerers.getEnum(Rarity.class, "_"+originalRarity.name()); // set its rarity to the overriding one.
        }

        final List<RarityCustom> rarities = Config.getInstance().getConfig().getRarities();
        if (rarities == null) {
            cir.setReturnValue(originalRarity);
        }
        for (int i = 0; i < rarities.size(); i++) {

            final RarityCustom rarity = rarities.get(i);
            if (rarity.getItems() == null) continue;
            for (int j = 0; j < rarity.getItems().size(); j++) { // For each item specified,
                final String item = rarity.getItems().get(j);
                if (Registry.ITEM.getId(getItem()).toString().equals(item)) { // set its rarity
                    if (rarity.getName().equals("COMMON") || rarity.getName().equals("UNCOMMON") || rarity.getName().equals("RARE") || rarity.getName().equals("EPIC")) { // if
                        newRarity = ClassTinkerers.getEnum(Rarity.class, "_"+rarity.getName());
                    } else {
                        newRarity = ClassTinkerers.getEnum(Rarity.class, rarity.getName());
                    }
                }
            }
        }

        if (newRarity != null) {
            cir.setReturnValue(newRarity);
        }
    }
}
