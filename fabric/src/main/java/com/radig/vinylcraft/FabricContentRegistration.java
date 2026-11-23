package com.radig.vinylcraft;

import com.radig.vinylcraft.block.ModBlocks;
import com.radig.vinylcraft.block.entity.ModBlockEntities;
import com.radig.vinylcraft.component.ModDataComponents;
import com.radig.vinylcraft.item.ModItems;
import com.radig.vinylcraft.sound.ModSounds;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

/** Fabric registry adapter for content definitions shared with Forge. */
public final class FabricContentRegistration {
    private FabricContentRegistration() {
    }

    public static void register() {
        ModBlocks.blockEntries().forEach((id, value) ->
                Registry.register(BuiltInRegistries.BLOCK, id, value));
        ModBlocks.blockItemEntries().forEach((id, value) ->
                Registry.register(BuiltInRegistries.ITEM, id, value));
        ModItems.itemEntries().forEach((id, value) ->
                Registry.register(BuiltInRegistries.ITEM, id, value));
        ModDataComponents.entries().forEach((id, value) ->
                registerDataComponent(id, value));
        ModSounds.entries().forEach((id, value) ->
                Registry.register(BuiltInRegistries.SOUND_EVENT, id, value));
        ModBlockEntities.entries().forEach((id, value) ->
                registerBlockEntity(id, value));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void registerDataComponent(net.minecraft.resources.Identifier id,
            net.minecraft.core.component.DataComponentType<?> value) {
        Registry.register((Registry) BuiltInRegistries.DATA_COMPONENT_TYPE, id, value);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void registerBlockEntity(net.minecraft.resources.Identifier id,
            net.minecraft.world.level.block.entity.BlockEntityType<?> value) {
        Registry.register((Registry) BuiltInRegistries.BLOCK_ENTITY_TYPE, id, value);
    }
}
