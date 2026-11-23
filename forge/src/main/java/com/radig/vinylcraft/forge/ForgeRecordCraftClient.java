package com.radig.vinylcraft.forge;

import com.mojang.blaze3d.platform.InputConstants;
import com.radig.vinylcraft.VinylCraft;
import com.radig.vinylcraft.block.entity.ModBlockEntities;
import com.radig.vinylcraft.client.config.VinylHudConfig;
import com.radig.vinylcraft.client.config.VinylHudSettingsScreen;
import com.radig.vinylcraft.client.library.MusicLibraryConfig;
import com.radig.vinylcraft.client.library.MusicLibraryScreen;
import com.radig.vinylcraft.client.music.RecordedAlbumStore;
import com.radig.vinylcraft.client.render.AlbumFrameBlockEntityRenderer;
import com.radig.vinylcraft.client.render.VinylPlayerBlockEntityRenderer;
import com.radig.vinylcraft.client.render.VinylPlayerButtonModel;
import com.radig.vinylcraft.client.render.VinylPlayerTonearmModel;
import com.radig.vinylcraft.client.render.VinylRecorderBlockEntityRenderer;
import com.radig.vinylcraft.client.render.VinylRecorderTonearmModel;
import com.radig.vinylcraft.client.sound.DiscmanSoundManager;
import com.radig.vinylcraft.client.sound.VinylPlayerSoundManager;
import com.radig.vinylcraft.item.HeadphonesItem;
import com.radig.vinylcraft.item.ModItems;
import com.radig.vinylcraft.item.VinylData;
import com.radig.vinylcraft.music.AlbumData;
import com.radig.vinylcraft.music.ModAlbums;
import com.radig.vinylcraft.platform.RecordCraftClientNetworking;
import com.radig.vinylcraft.recorder.VinylRecorderClientBridge;
import com.radig.vinylcraft.sound.VinylPlayerAudioBridge;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;

public final class ForgeRecordCraftClient {
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(VinylCraft.id("vinylcraft"));
    private static final KeyMapping OPEN_HUD = new KeyMapping(
            "key.vinylcraft.open_hud_settings", InputConstants.KEY_F6, CATEGORY);
    private static final KeyMapping OPEN_LIBRARY = new KeyMapping(
            "key.vinylcraft.open_library", InputConstants.KEY_F7, CATEGORY);

    private ForgeRecordCraftClient() {
    }

    public static void initialize() {
        RecordCraftClientNetworking.install(ForgeRecordCraftNetworking::sendToServer);
        RecordedAlbumStore.load();
        MusicLibraryConfig.load();
        VinylHudConfig.load();

        VinylRecorderClientBridge.setOpenScreenHandler(pos -> {
            Minecraft client = Minecraft.getInstance();
            if (client.gui.screen() == null)
                client.gui.setScreen(new com.radig.vinylcraft.client.recorder.VinylRecorderScreen(pos));
        });
        VinylRecorderClientBridge.setOpenAlbumInfoHandler(pos -> {
            Minecraft client = Minecraft.getInstance();
            if (client.gui.screen() == null)
                client.gui.setScreen(new com.radig.vinylcraft.client.recorder.VinylAlbumInfoScreen(pos));
        });
        VinylRecorderClientBridge.setCloneAlbumHandler(RecordedAlbumStore::cloneAlbumAsIndependent);
        VinylPlayerAudioBridge.setClientTicker(VinylPlayerSoundManager::tickPlayer);

        RegisterKeyMappingsEvent.BUS.addListener(event -> {
            event.register(OPEN_HUD);
            event.register(OPEN_LIBRARY);
        });
        TickEvent.ClientTickEvent.Post.BUS.addListener(event -> {
            Minecraft client = Minecraft.getInstance();
            while (OPEN_HUD.consumeClick()) {
                if (client.gui.screen() == null) client.gui.setScreen(new VinylHudSettingsScreen());
            }
            while (OPEN_LIBRARY.consumeClick()) {
                if (client.gui.screen() == null) client.gui.setScreen(new MusicLibraryScreen());
            }
            DiscmanSoundManager.tick(client);
        });
        ItemTooltipEvent.BUS.addListener(event -> appendVinylTooltip(event));
        EntityRenderersEvent.RegisterLayerDefinitions.BUS.addListener(event -> {
            event.registerLayerDefinition(VinylPlayerBlockEntityRenderer.TONEARM_LAYER, VinylPlayerTonearmModel::createLayer);
            event.registerLayerDefinition(VinylPlayerBlockEntityRenderer.BUTTON_LAYER, VinylPlayerButtonModel::createLayer);
            event.registerLayerDefinition(VinylRecorderBlockEntityRenderer.TONEARM_LAYER, VinylRecorderTonearmModel::createLayer);
        });
        EntityRenderersEvent.RegisterRenderers.BUS.addListener(event -> {
            event.registerBlockEntityRenderer(ModBlockEntities.VINYL_PLAYER, VinylPlayerBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.VINYL_RECORDER, VinylRecorderBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.ALBUM_FRAME, AlbumFrameBlockEntityRenderer::new);
        });
    }

    private static void appendVinylTooltip(ItemTooltipEvent event) {
        var stack = event.getItemStack();
        var lines = event.getToolTip();
        if (stack.is(ModItems.DISCMAN)) {
            lines.add(Component.literal("Reproductor portátil de vinilos").withStyle(ChatFormatting.GRAY));
            return;
        }
        if (stack.getItem() instanceof HeadphonesItem) {
            lines.add(Component.literal("Audífonos estéreo").withStyle(ChatFormatting.GRAY));
            return;
        }
        if (!stack.is(ModItems.BLANK_VINYL)) return;
        String albumId = VinylData.getAlbumId(stack);
        if (albumId == null) {
            lines.add(Component.literal("Sin grabar").withStyle(ChatFormatting.GRAY));
            lines.add(Component.literal("Usa este vinilo para crear un álbum").withStyle(ChatFormatting.DARK_GRAY));
            return;
        }
        AlbumData album = ModAlbums.get(albumId);
        if (album == null) {
            lines.add(Component.literal("Álbum no disponible en esta biblioteca").withStyle(ChatFormatting.RED));
            return;
        }
        if (album.artist() != null && !album.artist().isBlank())
            lines.add(Component.literal(album.artist()).withStyle(ChatFormatting.GRAY));
        int tracks = album.trackCount();
        lines.add(Component.literal(tracks + (tracks == 1 ? " pista" : " pistas")).withStyle(ChatFormatting.DARK_GRAY));
        long totalSeconds = album.totalDurationMillis() / 1000L;
        lines.add(Component.literal(String.format("%02d:%02d", totalSeconds / 60L, totalSeconds % 60L))
                .withStyle(ChatFormatting.DARK_GRAY));
        int wallSize = VinylData.getWallSize(stack);
        lines.add(Component.literal("Cuadro: " + wallSize + "x" + wallSize).withStyle(ChatFormatting.DARK_GRAY));
    }
}
