package net.ewan.testmod.item;

import net.ewan.testmod.TestMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);

    public static final RegistryObject<Item> INGOT_LEAD = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));

    public static final RegistryObject<Item> INGOT_DESH = ITEMS.register("desh_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));

    public static final RegistryObject<Item> INGOT_TIN = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));

    public static final RegistryObject<Item> INGOT_ALUMINUM = ITEMS.register("aluminum_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));

    public static final RegistryObject<Item> RAW_SILICON = ITEMS.register("raw_silicon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));

    public static final RegistryObject<Item> COMPRESSED_STEEL = ITEMS.register("compressed_steel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));

    public static final RegistryObject<Item> WAFER_ADVANCED = ITEMS.register("wafer_advanced",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));

    public static final RegistryObject<Item> WAFER_BASIC = ITEMS.register("wafer_basic",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));

    public static final RegistryObject<Item> NOSE_CONE = ITEMS.register("nose_cone",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));

    public static final RegistryObject<Item> FIN_ROCKET = ITEMS.register("fin_rocket",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));

    public static final RegistryObject<Item> HEAVY_PLATING = ITEMS.register("heavy_plating",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
