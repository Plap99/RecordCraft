package com.radig.vinylcraft.forge.mixin.client;

import com.radig.vinylcraft.forge.client.BecoyaRenderStateAccess;
import com.radig.vinylcraft.item.DiscmanData;
import com.radig.vinylcraft.item.HeadphonesItem;
import com.radig.vinylcraft.player.DiscmanEquipment;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AvatarRenderer.class)
public abstract class ForgeAvatarRendererMixin {
    @Inject(
            method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V",
            at = @At("TAIL"))
    private void recordcraft$extractAccessories(Avatar avatar, AvatarRenderState state,
            float partialTick, CallbackInfo ci) {
        boolean discman = false;
        boolean headphones = false;
        String color = "black";
        if (avatar instanceof Player player) {
            ItemStack equippedDiscman = DiscmanData.findEquipped(player);
            ItemStack equippedHeadphones = DiscmanEquipment.getHeadphones(player);
            discman = !equippedDiscman.isEmpty();
            headphones = equippedHeadphones.getItem() instanceof HeadphonesItem;
            if (equippedHeadphones.getItem() instanceof HeadphonesItem item) color = item.colorName();
        }
        BecoyaRenderStateAccess access = (BecoyaRenderStateAccess) state;
        access.recordcraft$setHasDiscman(discman);
        access.recordcraft$setHasHeadphones(headphones);
        access.recordcraft$setHeadphonesColor(color);
    }
}
