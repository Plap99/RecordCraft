package com.radig.vinylcraft.forge;

import com.radig.vinylcraft.VinylCraft;
import com.radig.vinylcraft.block.entity.VinylRecorderBlockEntity;
import com.radig.vinylcraft.item.DiscmanData;
import com.radig.vinylcraft.item.VinylData;
import com.radig.vinylcraft.network.DiscmanActionPayload;
import com.radig.vinylcraft.network.SetAlbumWallSizePayload;
import com.radig.vinylcraft.network.StartVinylRecordingPayload;
import com.radig.vinylcraft.player.DiscmanEquipmentHolder;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;

public final class ForgeRecordCraftNetworking {
    private static final Channel<CustomPacketPayload> CHANNEL = createChannel();

    private ForgeRecordCraftNetworking() {
    }

    private static Channel<CustomPacketPayload> createChannel() {
        var play = ChannelBuilder.named(VinylCraft.id("main"))
                .networkProtocolVersion(1)
                .optional()
                .payloadChannel()
                .play()
                .serverbound();
        play.addMain(DiscmanActionPayload.TYPE, DiscmanActionPayload.CODEC,
                (payload, context) -> handleDiscman(context.getSender(), payload));
        play.addMain(StartVinylRecordingPayload.TYPE, StartVinylRecordingPayload.CODEC,
                (payload, context) -> handleStartRecording(context.getSender(), payload));
        play.addMain(SetAlbumWallSizePayload.TYPE, SetAlbumWallSizePayload.CODEC,
                (payload, context) -> handleWallSize(context.getSender(), payload));
        return play.build();
    }

    public static void register() {
        // Creating the channel registers the payloads before joining worlds.
    }

    public static void sendToServer(CustomPacketPayload payload) {
        CHANNEL.send(payload, PacketDistributor.SERVER.noArg());
    }

    private static void handleDiscman(ServerPlayer player, DiscmanActionPayload payload) {
        if (player == null) return;
        ItemStack discman = DiscmanData.findEquipped(player);
        ItemStack vinyl = DiscmanData.findVinyl(player);
        if (discman.isEmpty()) return;
        boolean validVinyl = !vinyl.isEmpty() && VinylData.hasAlbum(vinyl);

        switch (payload.action()) {
            case DiscmanActionPayload.PLAY_PAUSE -> {
                if (validVinyl) {
                    if (DiscmanData.getState(discman) == DiscmanData.PLAYING) {
                        DiscmanData.setState(discman, DiscmanData.PAUSED);
                    } else {
                        long total = DiscmanData.getTotalTicks(vinyl);
                        if (total > 0L && DiscmanData.getPlaybackTicks(discman) >= total)
                            DiscmanData.setPlaybackTicks(discman, 0L);
                        DiscmanData.setState(discman, DiscmanData.PLAYING);
                    }
                }
            }
            case DiscmanActionPayload.STOP -> {
                DiscmanData.setState(discman, DiscmanData.STOPPED);
                DiscmanData.setPlaybackTicks(discman, 0L);
            }
            case DiscmanActionPayload.PREVIOUS -> { if (validVinyl) DiscmanData.previousTrack(discman, vinyl); }
            case DiscmanActionPayload.NEXT -> { if (validVinyl) DiscmanData.nextTrack(discman, vinyl); }
            case DiscmanActionPayload.SYNC_TICKS -> {
                if (validVinyl) {
                    long safe = Math.max(0L, payload.value());
                    long total = DiscmanData.getTotalTicks(vinyl);
                    if (total > 0L) safe = Math.min(safe, total);
                    DiscmanData.setPlaybackTicks(discman, safe);
                }
            }
            case DiscmanActionPayload.SET_VOLUME -> DiscmanData.setVolumePercent(
                    discman, (int) Math.max(0L, Math.min(100L, payload.value())));
            default -> { }
        }
        player.getInventory().setChanged();
        ((DiscmanEquipmentHolder) player).vinylcraft$getDiscmanEquipment().setChanged();
        if (payload.action() != DiscmanActionPayload.SYNC_TICKS) {
            player.inventoryMenu.broadcastFullState();
            if (player.containerMenu != player.inventoryMenu) player.containerMenu.broadcastFullState();
        }
    }

    private static void handleStartRecording(ServerPlayer player, StartVinylRecordingPayload payload) {
        if (player == null || !isNearby(player, payload.pos().getX(), payload.pos().getY(), payload.pos().getZ())) return;
        if (player.level().getBlockEntity(payload.pos()) instanceof VinylRecorderBlockEntity recorder)
            recorder.startRecording(payload.albumId(), payload.trackDurations());
    }

    private static void handleWallSize(ServerPlayer player, SetAlbumWallSizePayload payload) {
        if (player == null || !isNearby(player, payload.pos().getX(), payload.pos().getY(), payload.pos().getZ())) return;
        if (!(player.level().getBlockEntity(payload.pos()) instanceof VinylRecorderBlockEntity recorder)
                || !recorder.hasRecordedVinyl()) return;
        VinylData.setWallSize(recorder.getVinyl(), payload.size());
        recorder.setChanged();
    }

    private static boolean isNearby(ServerPlayer player, int x, int y, int z) {
        return player.distanceToSqr(x + 0.5D, y + 0.5D, z + 0.5D) <= 64.0D;
    }
}
