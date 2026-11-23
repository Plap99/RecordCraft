package com.radig.vinylcraft.forge;

import com.radig.vinylcraft.VinylCraft;
import com.radig.vinylcraft.platform.RecordCraftPaths;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VinylCraft.MOD_ID)
public final class ForgeRecordCraft {
    public ForgeRecordCraft(FMLJavaModLoadingContext context) {
        RecordCraftPaths.installConfigDirectory(FMLPaths.CONFIGDIR.get());
        ForgeContentRegistration.register(context.getModBusGroup());
        ForgeCreativeTabs.register(context.getModBusGroup());
        ForgeRecordCraftNetworking.register();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ForgeRecordCraftClient.initialize();
        }
        VinylCraft.LOGGER.info("RecordCraft Forge iniciado correctamente.");
    }
}
