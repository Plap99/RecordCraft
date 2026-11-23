package com.radig.vinylcraft.forge;

import com.radig.vinylcraft.VinylCraft;
import com.radig.vinylcraft.block.ModBlocks;
import com.radig.vinylcraft.block.entity.ModBlockEntities;
import com.radig.vinylcraft.component.ModDataComponents;
import com.radig.vinylcraft.item.ModItems;
import com.radig.vinylcraft.sound.ModSounds;

import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/** Forge DeferredRegister adapter for the shared RecordCraft definitions. */
public final class ForgeContentRegistration {
    private static final DeferredRegister<net.minecraft.world.level.block.Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, VinylCraft.MOD_ID);
    private static final DeferredRegister<net.minecraft.world.item.Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VinylCraft.MOD_ID);
    private static final DeferredRegister<net.minecraft.sounds.SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VinylCraft.MOD_ID);
    private static final DeferredRegister<net.minecraft.world.level.block.entity.BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VinylCraft.MOD_ID);
    private static final DeferredRegister<net.minecraft.core.component.DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, VinylCraft.MOD_ID);

    private ForgeContentRegistration() {
    }

    public static void register(BusGroup modEventBus) {
        ModBlocks.blockEntries().forEach((id, block) -> {
            var value = block;
            BLOCKS.register(id.getPath(), () -> value);
        });
        ModBlocks.blockItemEntries().forEach((id, item) -> {
            var value = item;
            ITEMS.register(id.getPath(), () -> value);
        });
        ModItems.itemEntries().forEach((id, item) -> {
            var value = item;
            ITEMS.register(id.getPath(), () -> value);
        });
        ModDataComponents.entries().forEach((id, component) -> {
            var value = component;
            DATA_COMPONENTS.register(id.getPath(), () -> value);
        });
        ModSounds.entries().forEach((id, sound) -> {
            var value = sound;
            SOUNDS.register(id.getPath(), () -> value);
        });
        ModBlockEntities.entries().forEach((id, blockEntity) -> {
            var value = blockEntity;
            BLOCK_ENTITIES.register(id.getPath(), () -> value);
        });

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        DATA_COMPONENTS.register(modEventBus);
        SOUNDS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
    }
}
