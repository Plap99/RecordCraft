package com.radig.vinylcraft.item;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import com.radig.vinylcraft.VinylCraft;
import com.radig.vinylcraft.block.ModBlocks;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import net.minecraft.world.item.Item;

public class ModItems {
    private static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();

    public static Item register(
            ResourceKey<Item> itemKey,
            Function<Item.Properties, Item> itemFactory,
            Item.Properties properties) {

        Item item =
                itemFactory.apply(
                        properties.setId(itemKey)
                );

        ITEMS.put(itemKey.identifier(), item);

        return item;
    }

    public static final Item BLANK_VINYL =
            register(
                    ModItemIds.BLANK_VINYL,
                    VinylItem::new,
                    new Item.Properties()
            );

    public static final Item DISCMAN =
            register(
                    ModItemIds.DISCMAN,
                    DiscmanItem::new,
                    new Item.Properties()
                            .stacksTo(1)
            );


public static final Item WHITE_HEADPHONES =
        register(
                ModItemIds.WHITE_HEADPHONES,
                properties -> new HeadphonesItem(properties, "white", 0xF9FFFE),
                new Item.Properties().stacksTo(1)
        );

public static final Item ORANGE_HEADPHONES =
        register(
                ModItemIds.ORANGE_HEADPHONES,
                properties -> new HeadphonesItem(properties, "orange", 0xF9801D),
                new Item.Properties().stacksTo(1)
        );

public static final Item MAGENTA_HEADPHONES =
        register(
                ModItemIds.MAGENTA_HEADPHONES,
                properties -> new HeadphonesItem(properties, "magenta", 0xC74EBD),
                new Item.Properties().stacksTo(1)
        );

public static final Item LIGHT_BLUE_HEADPHONES =
        register(
                ModItemIds.LIGHT_BLUE_HEADPHONES,
                properties -> new HeadphonesItem(properties, "light_blue", 0x3AB3DA),
                new Item.Properties().stacksTo(1)
        );

public static final Item YELLOW_HEADPHONES =
        register(
                ModItemIds.YELLOW_HEADPHONES,
                properties -> new HeadphonesItem(properties, "yellow", 0xFED83D),
                new Item.Properties().stacksTo(1)
        );

public static final Item LIME_HEADPHONES =
        register(
                ModItemIds.LIME_HEADPHONES,
                properties -> new HeadphonesItem(properties, "lime", 0x80C71F),
                new Item.Properties().stacksTo(1)
        );

public static final Item PINK_HEADPHONES =
        register(
                ModItemIds.PINK_HEADPHONES,
                properties -> new HeadphonesItem(properties, "pink", 0xF38BAA),
                new Item.Properties().stacksTo(1)
        );

public static final Item GRAY_HEADPHONES =
        register(
                ModItemIds.GRAY_HEADPHONES,
                properties -> new HeadphonesItem(properties, "gray", 0x474F52),
                new Item.Properties().stacksTo(1)
        );

public static final Item LIGHT_GRAY_HEADPHONES =
        register(
                ModItemIds.LIGHT_GRAY_HEADPHONES,
                properties -> new HeadphonesItem(properties, "light_gray", 0x9D9D97),
                new Item.Properties().stacksTo(1)
        );

public static final Item CYAN_HEADPHONES =
        register(
                ModItemIds.CYAN_HEADPHONES,
                properties -> new HeadphonesItem(properties, "cyan", 0x169C9C),
                new Item.Properties().stacksTo(1)
        );

public static final Item PURPLE_HEADPHONES =
        register(
                ModItemIds.PURPLE_HEADPHONES,
                properties -> new HeadphonesItem(properties, "purple", 0x8932B8),
                new Item.Properties().stacksTo(1)
        );

public static final Item BLUE_HEADPHONES =
        register(
                ModItemIds.BLUE_HEADPHONES,
                properties -> new HeadphonesItem(properties, "blue", 0x3C44AA),
                new Item.Properties().stacksTo(1)
        );

public static final Item BROWN_HEADPHONES =
        register(
                ModItemIds.BROWN_HEADPHONES,
                properties -> new HeadphonesItem(properties, "brown", 0x835432),
                new Item.Properties().stacksTo(1)
        );

public static final Item GREEN_HEADPHONES =
        register(
                ModItemIds.GREEN_HEADPHONES,
                properties -> new HeadphonesItem(properties, "green", 0x5E7C16),
                new Item.Properties().stacksTo(1)
        );

public static final Item RED_HEADPHONES =
        register(
                ModItemIds.RED_HEADPHONES,
                properties -> new HeadphonesItem(properties, "red", 0xB02E26),
                new Item.Properties().stacksTo(1)
        );

public static final Item BLACK_HEADPHONES =
        register(
                ModItemIds.BLACK_HEADPHONES,
                properties -> new HeadphonesItem(properties, "black", 0x1D1D21),
                new Item.Properties().stacksTo(1)
        );

    public static void registerModItems() {

        VinylCraft.LOGGER.info(
                "Registering VinylCraft items..."
        );
    }

    public static Map<Identifier, Item> itemEntries() {
        return Collections.unmodifiableMap(ITEMS);
    }
}
