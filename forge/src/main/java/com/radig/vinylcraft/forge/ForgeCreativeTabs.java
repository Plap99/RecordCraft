package com.radig.vinylcraft.forge;

import com.radig.vinylcraft.VinylCraft;
import com.radig.vinylcraft.block.ModBlocks;
import com.radig.vinylcraft.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;

public final class ForgeCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VinylCraft.MOD_ID);
    private static final java.util.function.Supplier<CreativeModeTab> RECORDCRAFT = TABS.register(
            "recordcraft",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.vinylcraft"))
                    .icon(() -> new ItemStack(ModItems.BLANK_VINYL))
                    .displayItems((parameters, output) -> {
                        ModItems.itemEntries().values().forEach(output::accept);
                        ModBlocks.blockItemEntries().values().forEach(output::accept);
                    })
                    .build()
    );

    private ForgeCreativeTabs() {
    }

    public static void register(BusGroup modBusGroup) {
        TABS.register(modBusGroup);
    }
}
