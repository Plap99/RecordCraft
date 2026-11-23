package com.radig.vinylcraft.block;

import com.radig.vinylcraft.VinylCraft;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import net.minecraft.world.item.BlockItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

    private static final Map<Identifier, Block> BLOCKS = new LinkedHashMap<>();
    private static final Map<Identifier, BlockItem> BLOCK_ITEMS = new LinkedHashMap<>();

    /*
     * =====================================================
     * VINYL PLAYERS
     * =====================================================
     */

    public static final Block OAK_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.OAK_VINYL_PLAYER);

    public static final Block SPRUCE_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.SPRUCE_VINYL_PLAYER);

    public static final Block BIRCH_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.BIRCH_VINYL_PLAYER);

    public static final Block JUNGLE_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.JUNGLE_VINYL_PLAYER);

    public static final Block ACACIA_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.ACACIA_VINYL_PLAYER);

    public static final Block DARK_OAK_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.DARK_OAK_VINYL_PLAYER);

    public static final Block MANGROVE_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.MANGROVE_VINYL_PLAYER);

    public static final Block CHERRY_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.CHERRY_VINYL_PLAYER);

    public static final Block PALE_OAK_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.PALE_OAK_VINYL_PLAYER);

    public static final Block POPLAR_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.POPLAR_VINYL_PLAYER);

    public static final Block BAMBOO_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.BAMBOO_VINYL_PLAYER);

    public static final Block CRIMSON_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.CRIMSON_VINYL_PLAYER);

    public static final Block WARPED_VINYL_PLAYER =
            createVinylPlayer(ModBlockItemIds.WARPED_VINYL_PLAYER);

    /*
     * =====================================================
     * VINYL RECORDERS
     * =====================================================
     */

    public static final Block OAK_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.OAK_VINYL_RECORDER);

    public static final Block SPRUCE_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.SPRUCE_VINYL_RECORDER);

    public static final Block BIRCH_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.BIRCH_VINYL_RECORDER);

    public static final Block JUNGLE_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.JUNGLE_VINYL_RECORDER);

    public static final Block ACACIA_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.ACACIA_VINYL_RECORDER);

    public static final Block DARK_OAK_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.DARK_OAK_VINYL_RECORDER);

    public static final Block MANGROVE_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.MANGROVE_VINYL_RECORDER);

    public static final Block CHERRY_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.CHERRY_VINYL_RECORDER);

    public static final Block PALE_OAK_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.PALE_OAK_VINYL_RECORDER);

    public static final Block POPLAR_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.POPLAR_VINYL_RECORDER);

    public static final Block BAMBOO_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.BAMBOO_VINYL_RECORDER);

    public static final Block CRIMSON_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.CRIMSON_VINYL_RECORDER);

    public static final Block WARPED_VINYL_RECORDER =
            createVinylRecorder(ModBlockItemIds.WARPED_VINYL_RECORDER);

    /** Bloque técnico sin item: cada pieza representa una zona del póster mural. */
    public static final Block ALBUM_FRAME = createAlbumFrame();

    /*
     * =====================================================
     * CREACIÓN
     * =====================================================
     */

    private static Block createVinylPlayer(
            net.minecraft.references.BlockItemId id) {

        Block block = new VinylPlayerBlock(
                BlockBehaviour.Properties.of()
                        .strength(1.2F, 3.0F)
                        .noOcclusion()
                        .setId(id.block())
        );
        BLOCKS.put(id.block().identifier(), block);
        BLOCK_ITEMS.put(
                id.item().identifier(),
                new BlockItem(block, new BlockItem.Properties().setId(id.item()))
        );
        return block;
    }

    private static Block createVinylRecorder(
            net.minecraft.references.BlockItemId id) {

        Block block = new VinylRecorderBlock(
                BlockBehaviour.Properties.of()
                        .strength(1.6F, 4.0F)
                        .noOcclusion()
                        .setId(id.block())
        );
        BLOCKS.put(id.block().identifier(), block);
        BLOCK_ITEMS.put(
                id.item().identifier(),
                new BlockItem(block, new BlockItem.Properties().setId(id.item()))
        );
        return block;
    }

    private static Block createAlbumFrame() {
        Block block = new AlbumFrameBlock(
                BlockBehaviour.Properties.of()
                        .noCollision()
                        .noOcclusion()
                        .instabreak()
                        .noLootTable()
                        .setId(ModBlockItemIds.ALBUM_FRAME.block())
        );
        BLOCKS.put(ModBlockItemIds.ALBUM_FRAME.block().identifier(), block);
        return block;
    }

    public static Map<Identifier, Block> blockEntries() {
        return Collections.unmodifiableMap(BLOCKS);
    }

    public static Map<Identifier, BlockItem> blockItemEntries() {
        return Collections.unmodifiableMap(BLOCK_ITEMS);
    }

    /*
     * =====================================================
     * REGISTRO
     * =====================================================
     */

    public static void registerModBlocks() {

        VinylCraft.LOGGER.info(
                "Registrando bloques de VinylCraft..."
        );

        registerBlock(ModBlockItemIds.OAK_VINYL_PLAYER, OAK_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.SPRUCE_VINYL_PLAYER, SPRUCE_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.BIRCH_VINYL_PLAYER, BIRCH_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.JUNGLE_VINYL_PLAYER, JUNGLE_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.ACACIA_VINYL_PLAYER, ACACIA_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.DARK_OAK_VINYL_PLAYER, DARK_OAK_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.MANGROVE_VINYL_PLAYER, MANGROVE_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.CHERRY_VINYL_PLAYER, CHERRY_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.PALE_OAK_VINYL_PLAYER, PALE_OAK_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.POPLAR_VINYL_PLAYER, POPLAR_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.BAMBOO_VINYL_PLAYER, BAMBOO_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.CRIMSON_VINYL_PLAYER, CRIMSON_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.WARPED_VINYL_PLAYER, WARPED_VINYL_PLAYER);

        registerBlock(ModBlockItemIds.OAK_VINYL_RECORDER, OAK_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.SPRUCE_VINYL_RECORDER, SPRUCE_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.BIRCH_VINYL_RECORDER, BIRCH_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.JUNGLE_VINYL_RECORDER, JUNGLE_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.ACACIA_VINYL_RECORDER, ACACIA_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.DARK_OAK_VINYL_RECORDER, DARK_OAK_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.MANGROVE_VINYL_RECORDER, MANGROVE_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.CHERRY_VINYL_RECORDER, CHERRY_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.PALE_OAK_VINYL_RECORDER, PALE_OAK_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.POPLAR_VINYL_RECORDER, POPLAR_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.BAMBOO_VINYL_RECORDER, BAMBOO_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.CRIMSON_VINYL_RECORDER, CRIMSON_VINYL_RECORDER);

        registerBlock(ModBlockItemIds.WARPED_VINYL_RECORDER, WARPED_VINYL_RECORDER);

        Registry.register(
                BuiltInRegistries.BLOCK,
                ModBlockItemIds.ALBUM_FRAME.block(),
                ALBUM_FRAME
        );

    }

    private static void registerBlock(
            net.minecraft.references.BlockItemId id,
            Block block) {

        Registry.register(
                BuiltInRegistries.BLOCK,
                id.block(),
                block
        );

        BlockItem blockItem =
                new BlockItem(
                        block,
                        new BlockItem.Properties()
                                .setId(id.item())
                );

        Registry.register(
                BuiltInRegistries.ITEM,
                id.item(),
                blockItem
        );
    }
}
