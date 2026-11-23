package com.radig.vinylcraft;

import com.radig.vinylcraft.block.ModBlocks;
import com.radig.vinylcraft.item.ModItems;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;

/** Fabric creative-tab hooks; Forge supplies its own tab event adapter. */
public final class FabricCreativeTabs {
    private FabricCreativeTabs() {
    }

    public static void register() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(tab -> {
                    tab.accept(ModItems.BLANK_VINYL);
                    tab.accept(ModItems.DISCMAN);
                    tab.accept(ModItems.WHITE_HEADPHONES);
                    tab.accept(ModItems.ORANGE_HEADPHONES);
                    tab.accept(ModItems.MAGENTA_HEADPHONES);
                    tab.accept(ModItems.LIGHT_BLUE_HEADPHONES);
                    tab.accept(ModItems.YELLOW_HEADPHONES);
                    tab.accept(ModItems.LIME_HEADPHONES);
                    tab.accept(ModItems.PINK_HEADPHONES);
                    tab.accept(ModItems.GRAY_HEADPHONES);
                    tab.accept(ModItems.LIGHT_GRAY_HEADPHONES);
                    tab.accept(ModItems.CYAN_HEADPHONES);
                    tab.accept(ModItems.PURPLE_HEADPHONES);
                    tab.accept(ModItems.BLUE_HEADPHONES);
                    tab.accept(ModItems.BROWN_HEADPHONES);
                    tab.accept(ModItems.GREEN_HEADPHONES);
                    tab.accept(ModItems.RED_HEADPHONES);
                    tab.accept(ModItems.BLACK_HEADPHONES);
                });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register(tab -> {
                    tab.accept(ModBlocks.OAK_VINYL_PLAYER);
                    tab.accept(ModBlocks.SPRUCE_VINYL_PLAYER);
                    tab.accept(ModBlocks.BIRCH_VINYL_PLAYER);
                    tab.accept(ModBlocks.JUNGLE_VINYL_PLAYER);
                    tab.accept(ModBlocks.ACACIA_VINYL_PLAYER);
                    tab.accept(ModBlocks.DARK_OAK_VINYL_PLAYER);
                    tab.accept(ModBlocks.MANGROVE_VINYL_PLAYER);
                    tab.accept(ModBlocks.CHERRY_VINYL_PLAYER);
                    tab.accept(ModBlocks.PALE_OAK_VINYL_PLAYER);
                    tab.accept(ModBlocks.POPLAR_VINYL_PLAYER);
                    tab.accept(ModBlocks.BAMBOO_VINYL_PLAYER);
                    tab.accept(ModBlocks.CRIMSON_VINYL_PLAYER);
                    tab.accept(ModBlocks.WARPED_VINYL_PLAYER);

                    tab.accept(ModBlocks.OAK_VINYL_RECORDER);
                    tab.accept(ModBlocks.SPRUCE_VINYL_RECORDER);
                    tab.accept(ModBlocks.BIRCH_VINYL_RECORDER);
                    tab.accept(ModBlocks.JUNGLE_VINYL_RECORDER);
                    tab.accept(ModBlocks.ACACIA_VINYL_RECORDER);
                    tab.accept(ModBlocks.DARK_OAK_VINYL_RECORDER);
                    tab.accept(ModBlocks.MANGROVE_VINYL_RECORDER);
                    tab.accept(ModBlocks.CHERRY_VINYL_RECORDER);
                    tab.accept(ModBlocks.PALE_OAK_VINYL_RECORDER);
                    tab.accept(ModBlocks.POPLAR_VINYL_RECORDER);
                    tab.accept(ModBlocks.BAMBOO_VINYL_RECORDER);
                    tab.accept(ModBlocks.CRIMSON_VINYL_RECORDER);
                    tab.accept(ModBlocks.WARPED_VINYL_RECORDER);
                });
    }
}
