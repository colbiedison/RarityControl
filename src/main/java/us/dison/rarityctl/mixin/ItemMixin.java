package us.dison.rarityctl.mixin;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow public abstract Rarity getRarity(ItemStack stack);

    @Shadow @Final private Rarity rarity;

    @Inject(method = "<init>(Lnet/minecraft/item/Item$Settings;)V", at = @At("RETURN"))
    private void init(Item.Settings settings, CallbackInfo ci) {
        ((ItemAccessor) this).setRarity(ClassTinkerers.getEnum(Rarity.class, "CUSTOMRARITY"));
    }
}
