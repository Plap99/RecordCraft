package com.radig.vinylcraft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.Identifier;

/** Shared mod identity and logging; loader entry points live in their modules. */
public final class VinylCraft {
    public static final String MOD_ID = "vinylcraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private VinylCraft() {
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
