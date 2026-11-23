package com.radig.vinylcraft.client.render.becoya;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.radig.vinylcraft.VinylCraft;
import com.radig.vinylcraft.forge.client.BecoyaRenderStateAccess;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.resources.Identifier;

public final class BecoyaAccessoriesLayer extends RenderLayer<AvatarRenderState, PlayerModel> {
    public static final ModelLayerLocation DISCMAN_LAYER = new ModelLayerLocation(VinylCraft.id("becoya_discman_wearable"), "main");
    public static final ModelLayerLocation HEADPHONES_LAYER = new ModelLayerLocation(VinylCraft.id("becoya_headphones"), "main");
    public static final ModelLayerLocation CABLE_LAYER = new ModelLayerLocation(VinylCraft.id("becoya_headphone_cable"), "main");
    private static final Identifier DISCMAN_TEXTURE = VinylCraft.id("textures/entity/becoya_discman_wearable.png");
    private static final Identifier CABLE_TEXTURE = VinylCraft.id("textures/entity/becoya_headphone_cable.png");

    private final BecoyaAccessoryModel discmanModel;
    private final BecoyaAccessoryModel headphonesModel;
    private final BecoyaAccessoryModel cableModel;

    public BecoyaAccessoriesLayer(AvatarRenderer<?> renderer, EntityModelSet models) {
        super(renderer);
        discmanModel = new BecoyaAccessoryModel(models.bakeLayer(DISCMAN_LAYER));
        headphonesModel = new BecoyaAccessoryModel(models.bakeLayer(HEADPHONES_LAYER));
        cableModel = new BecoyaAccessoryModel(models.bakeLayer(CABLE_LAYER));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight,
            AvatarRenderState state, float limbAngle, float limbDistance) {
        BecoyaRenderStateAccess data = (BecoyaRenderStateAccess) state;
        boolean discman = data.recordcraft$hasDiscman();
        boolean headphones = data.recordcraft$hasHeadphones();
        if ((!discman && !headphones) || state.isInvisible) return;
        int overlay = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
        PlayerModel parent = getParentModel();
        if (headphones) {
            Identifier texture = VinylCraft.id("textures/entity/headphones/" + data.recordcraft$headphonesColor() + ".png");
            poseStack.pushPose();
            parent.head.translateAndRotate(poseStack);
            collector.submitModel(headphonesModel, state, poseStack, texture, packedLight, overlay, state.outlineColor);
            poseStack.popPose();
        }
        if (discman && headphones) {
            poseStack.pushPose();
            parent.body.translateAndRotate(poseStack);
            collector.submitModel(cableModel, state, poseStack, CABLE_TEXTURE, packedLight, overlay, state.outlineColor);
            poseStack.popPose();
        }
        if (discman) {
            poseStack.pushPose();
            parent.body.translateAndRotate(poseStack);
            poseStack.translate(0.12D, 0.58D, -0.28D);
            poseStack.rotateDegrees(Axis.YP, -1.5F);
            collector.submitModel(discmanModel, state, poseStack, DISCMAN_TEXTURE, packedLight, overlay, state.outlineColor);
            poseStack.popPose();
        }
    }
}
