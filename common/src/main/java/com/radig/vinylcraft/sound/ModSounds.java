package com.radig.vinylcraft.sound;

import com.radig.vinylcraft.VinylCraft;

import java.util.Map;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {

    public static final Identifier GET_YOUR_SHINE_ON_ID =
            VinylCraft.id("get_your_shine_on");

    public static final SoundEvent GET_YOUR_SHINE_ON =
            SoundEvent.createVariableRangeEvent(
                    GET_YOUR_SHINE_ON_ID
            );

    public static void registerModSounds() {
        VinylCraft.LOGGER.info(
                "Registering sounds for {}",
                VinylCraft.MOD_ID
        );
    }

    public static Map<Identifier, SoundEvent> entries() {
        return Map.of(GET_YOUR_SHINE_ON_ID, GET_YOUR_SHINE_ON);
    }
}
