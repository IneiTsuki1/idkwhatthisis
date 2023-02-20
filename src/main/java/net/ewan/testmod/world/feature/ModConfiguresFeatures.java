package net.ewan.testmod.world.feature;

import com.google.common.base.Suppliers;
import net.ewan.testmod.TestMod;
import net.ewan.testmod.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguresFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, TestMod.MOD_ID);


    /* */

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TIN_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_DEEPSLATE_TIN_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ORE_TIN.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ALUMINUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ALUMINUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_DEEPSLATE_ALUMINUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ORE_ALUMINUM.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_SILICON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILICON_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_DEEPSLATE_SILICON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ORE_SILICON.get().defaultBlockState())));


    /*Tin ore*/

    public static final RegistryObject<ConfiguredFeature<?, ?>> TIN_ORE = CONFIGURED_FEATURES.register("tin_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_TIN_ORES.get(), 7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_ORE_TIN = CONFIGURED_FEATURES.register("deepslate_ore_tin",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_DEEPSLATE_TIN_ORES.get(), 7)));


    /*Aluminum ore*/

    public static final RegistryObject<ConfiguredFeature<?, ?>> ALUMINUM_ORE = CONFIGURED_FEATURES.register("aluminum_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ALUMINUM_ORES.get(), 9)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_ORE_ALUMINUM = CONFIGURED_FEATURES.register("deepslate_ore_aluminum",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_DEEPSLATE_ALUMINUM_ORES.get(), 9)));

    /*Silicon ore*/

    public static final RegistryObject<ConfiguredFeature<?, ?>> SILICON_ORE = CONFIGURED_FEATURES.register("silicon_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_SILICON_ORES.get(), 9)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_ORE_SILICON = CONFIGURED_FEATURES.register("deepslate_ore_silicon",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_DEEPSLATE_SILICON_ORES.get(), 9)));


    /* */
    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }

}
