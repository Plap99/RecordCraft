package com.radig.vinylcraft;

import com.radig.vinylcraft.platform.RecordCraftPaths;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import com.radig.vinylcraft.network.VinylRecorderNetworking;
import com.radig.vinylcraft.network.DiscmanNetworking;

public final class FabricRecordCraft implements ModInitializer {

    @Override
    public void onInitialize() {
        RecordCraftPaths.installConfigDirectory(FabricLoader.getInstance().getConfigDir());

        FabricContentRegistration.register();
        FabricCreativeTabs.register();
        VinylRecorderNetworking.register();
        DiscmanNetworking.register();

        VinylCraft.LOGGER.info("RecordCraft Fabric loaded successfully!");
    }
}
