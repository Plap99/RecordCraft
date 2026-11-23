package com.radig.vinylcraft.component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.mojang.serialization.Codec;
import com.radig.vinylcraft.VinylCraft;

import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.core.component.DataComponentType;

/** Shared data-component definitions; each loader registers the resulting entries. */
public final class ModDataComponents {
    private static final Map<Identifier, DataComponentType<?>> ENTRIES = new LinkedHashMap<>();

    public static final DataComponentType<String> ALBUM_ID = register(
            "album_id",
            DataComponentType.<String>builder()
                    .persistent(Codec.STRING)
                    .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                    .build()
    );

    public static final DataComponentType<String> ALBUM_TRACK_DURATIONS = register(
            "album_track_durations",
            DataComponentType.<String>builder()
                    .persistent(Codec.STRING)
                    .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                    .build()
    );

    public static final DataComponentType<Integer> ALBUM_WALL_SIZE = register(
            "album_wall_size",
            DataComponentType.<Integer>builder()
                    .persistent(Codec.intRange(1, 10))
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
                    .build()
    );

    public static final DataComponentType<Boolean> DISCMAN_EQUIPPED = register(
            "discman_equipped",
            DataComponentType.<Boolean>builder()
                    .persistent(Codec.BOOL)
                    .networkSynchronized(ByteBufCodecs.BOOL)
                    .build()
    );

    public static final DataComponentType<Integer> DISCMAN_STATE = register(
            "discman_state",
            DataComponentType.<Integer>builder()
                    .persistent(Codec.intRange(0, 2))
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
                    .build()
    );

    public static final DataComponentType<Long> DISCMAN_PLAYBACK_TICKS = register(
            "discman_playback_ticks",
            DataComponentType.<Long>builder()
                    .persistent(Codec.LONG)
                    .networkSynchronized(ByteBufCodecs.VAR_LONG)
                    .build()
    );

    public static final DataComponentType<Integer> DISCMAN_VOLUME = register(
            "discman_volume",
            DataComponentType.<Integer>builder()
                    .persistent(Codec.intRange(0, 100))
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
                    .build()
    );

    private ModDataComponents() {
    }

    private static <T> DataComponentType<T> register(String path, DataComponentType<T> component) {
        ENTRIES.put(VinylCraft.id(path), component);
        return component;
    }

    public static Map<Identifier, DataComponentType<?>> entries() {
        return Collections.unmodifiableMap(ENTRIES);
    }
}
