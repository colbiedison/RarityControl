package us.dison.rarityctl.mixin.client;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    @Shadow public abstract void sendMessage(Text message, boolean actionBar);

    @Shadow public abstract void sendChatMessage(String message);

    @Inject(method = "Lnet/minecraft/client/network/ClientPlayerEntity;tick()V", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
//        this.sendMessage(Text.of("§ztest"), true);
//        this.sendChatMessage("§ztest");
    }
}
