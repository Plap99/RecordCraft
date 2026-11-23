package com.radig.vinylcraft.forge.mixin.client;

import com.radig.vinylcraft.forge.client.BecoyaRenderStateAccess;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AvatarRenderState.class)
public abstract class ForgeAvatarRenderStateMixin implements BecoyaRenderStateAccess {
    @Unique private boolean recordcraft$hasDiscman;
    @Unique private boolean recordcraft$hasHeadphones;
    @Unique private String recordcraft$headphonesColor = "black";

    @Override public boolean recordcraft$hasDiscman() { return recordcraft$hasDiscman; }
    @Override public void recordcraft$setHasDiscman(boolean value) { recordcraft$hasDiscman = value; }
    @Override public boolean recordcraft$hasHeadphones() { return recordcraft$hasHeadphones; }
    @Override public void recordcraft$setHasHeadphones(boolean value) { recordcraft$hasHeadphones = value; }
    @Override public String recordcraft$headphonesColor() { return recordcraft$headphonesColor; }
    @Override public void recordcraft$setHeadphonesColor(String value) { recordcraft$headphonesColor = value; }
}
