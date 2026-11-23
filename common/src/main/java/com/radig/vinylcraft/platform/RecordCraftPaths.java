package com.radig.vinylcraft.platform;

import java.nio.file.Path;
import java.util.Objects;

/** Loader-provided paths used by shared configuration and local music storage. */
public final class RecordCraftPaths {
    private static Path configDirectory;

    private RecordCraftPaths() {
    }

    public static void installConfigDirectory(Path path) {
        configDirectory = Objects.requireNonNull(path, "path");
    }

    public static Path configDirectory() {
        return Objects.requireNonNull(
                configDirectory,
                "RecordCraft config directory was not installed by the loader"
        );
    }
}
