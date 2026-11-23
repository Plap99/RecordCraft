package com.radig.vinylcraft.platform;

import java.util.Objects;
import java.util.function.Consumer;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/** Client packet bridge installed by Fabric or Forge during client startup. */
public final class RecordCraftClientNetworking {
    private static Consumer<CustomPacketPayload> sender;

    private RecordCraftClientNetworking() {
    }

    public static void install(Consumer<CustomPacketPayload> clientSender) {
        sender = Objects.requireNonNull(clientSender, "clientSender");
    }

    public static void send(CustomPacketPayload payload) {
        Consumer<CustomPacketPayload> current = sender;
        if (current == null) {
            throw new IllegalStateException("RecordCraft client networking is not initialized");
        }
        current.accept(payload);
    }
}
