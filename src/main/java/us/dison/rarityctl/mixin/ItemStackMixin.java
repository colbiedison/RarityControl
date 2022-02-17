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

    @Inject(method = "getRarity()Lnet/minecraft/util/Rarity;", at = @At("RETURN"), cancellable = true)
    private void getRarity(CallbackInfoReturnable<Rarity> cir) {

        final List<RarityCustom> rarities = Config.getInstance().getConfig().getRarities();
        for (int i = 0; i < rarities.size(); i++) { // For each custom rarity,

            final RarityCustom rarity = rarities.get(i);
            for (int j = 0; j < rarity.getItems().size(); j++) { // for each item specified,

                final String item = rarity.getItems().get(j);
                if (Registry.ITEM.getId(getItem()).toString().equals(item)) { // set its rarity
                    cir.setReturnValue(ClassTinkerers.getEnum(Rarity.class, rarity.getName()));
                }
            }
        }

    }
}
