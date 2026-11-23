package com.radig.vinylcraft.block.entity;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.radig.vinylcraft.VinylCraft;
import com.radig.vinylcraft.block.ModBlocks;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

/** Shared block-entity definitions; Fabric and Forge register these values separately. */
public final class ModBlockEntities {
    private static final Map<Identifier, BlockEntityType<?>> ENTRIES = new LinkedHashMap<>();

    public static final BlockEntityType<VinylPlayerBlockEntity> VINYL_PLAYER = register(
            "vinyl_player",
            new BlockEntityType<>(
                    VinylPlayerBlockEntity::new,
                    Set.of(ModBlocks.OAK_VINYL_PLAYER,
                    ModBlocks.SPRUCE_VINYL_PLAYER,
                    ModBlocks.BIRCH_VINYL_PLAYER,
                    ModBlocks.JUNGLE_VINYL_PLAYER,
                    ModBlocks.ACACIA_VINYL_PLAYER,
                    ModBlocks.DARK_OAK_VINYL_PLAYER,
                    ModBlocks.MANGROVE_VINYL_PLAYER,
                    ModBlocks.CHERRY_VINYL_PLAYER,
                    ModBlocks.PALE_OAK_VINYL_PLAYER,
                    ModBlocks.POPLAR_VINYL_PLAYER,
                    ModBlocks.BAMBOO_VINYL_PLAYER,
                    ModBlocks.CRIMSON_VINYL_PLAYER,
                    ModBlocks.WARPED_VINYL_PLAYER)
            )
    );

    public static final BlockEntityType<VinylRecorderBlockEntity> VINYL_RECORDER = register(
            "vinyl_recorder",
            new BlockEntityType<>(
                    VinylRecorderBlockEntity::new,
                    Set.of(ModBlocks.OAK_VINYL_RECORDER,
                    ModBlocks.SPRUCE_VINYL_RECORDER,
                    ModBlocks.BIRCH_VINYL_RECORDER,
                    ModBlocks.JUNGLE_VINYL_RECORDER,
                    ModBlocks.ACACIA_VINYL_RECORDER,
                    ModBlocks.DARK_OAK_VINYL_RECORDER,
                    ModBlocks.MANGROVE_VINYL_RECORDER,
                    ModBlocks.CHERRY_VINYL_RECORDER,
                    ModBlocks.PALE_OAK_VINYL_RECORDER,
                    ModBlocks.POPLAR_VINYL_RECORDER,
                    ModBlocks.BAMBOO_VINYL_RECORDER,
                    ModBlocks.CRIMSON_VINYL_RECORDER,
                    ModBlocks.WARPED_VINYL_RECORDER)
            )
    );

    public static final BlockEntityType<AlbumFrameBlockEntity> ALBUM_FRAME = register(
            "album_frame",
            new BlockEntityType<>(
                    AlbumFrameBlockEntity::new,
                    Set.of(ModBlocks.ALBUM_FRAME)
            )
    );

    private ModBlockEntities() {
    }

    private static <T extends net.minecraft.world.level.block.entity.BlockEntity>
    BlockEntityType<T> register(String path, BlockEntityType<T> type) {
        ENTRIES.put(VinylCraft.id(path), type);
        return type;
    }

    public static Map<Identifier, BlockEntityType<?>> entries() {
        return Collections.unmodifiableMap(ENTRIES);
    }
}
