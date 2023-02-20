package net.ewan.testmod.world.feature;

import net.ewan.testmod.TestMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, TestMod.MOD_ID);


    public static final RegistryObject<PlacedFeature> TIN_ORE_PLACED = PLACED_FEATURES.register("tin_ore",
            () -> new PlacedFeature(ModConfiguresFeatures.TIN_ORE.getHolder().get(),
                    commonOrePlacement(11,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(9), VerticalAnchor.absolute(60)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_ORE_TIN_PLACED = PLACED_FEATURES.register("deepslate_ore_tin",
            () -> new PlacedFeature(ModConfiguresFeatures.DEEPSLATE_ORE_TIN.getHolder().get(),
                    commonOrePlacement(12,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-55), VerticalAnchor.absolute(8)))));

    public static final RegistryObject<PlacedFeature> ALUMINUM_ORE_PLACED = PLACED_FEATURES.register("aluminum_ore",
            () -> new PlacedFeature(ModConfiguresFeatures.ALUMINUM_ORE.getHolder().get(),
                    commonOrePlacement(12,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(9), VerticalAnchor.absolute(50)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_ORE_ALUMINUM = PLACED_FEATURES.register("deepslate_ore_aluminum",
            () -> new PlacedFeature(ModConfiguresFeatures.DEEPSLATE_ORE_ALUMINUM.getHolder().get(),
                    commonOrePlacement(12,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-30), VerticalAnchor.absolute(8)))));

    public static final RegistryObject<PlacedFeature> SILICON_ORE_PLACED = PLACED_FEATURES.register("silicon_ore",
            () -> new PlacedFeature(ModConfiguresFeatures.SILICON_ORE.getHolder().get(),
                    commonOrePlacement(15,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(9), VerticalAnchor.absolute(60)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_ORE_SILICON = PLACED_FEATURES.register("deepslate_ore_silicon",
            () -> new PlacedFeature(ModConfiguresFeatures.DEEPSLATE_ORE_SILICON.getHolder().get(),
                    commonOrePlacement(15,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-55), VerticalAnchor.absolute(8)))));


    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }





    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }

}
