package net.ewan.testmod.recipe;

import net.ewan.testmod.TestMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TestMod.MOD_ID);

        public static final RegistryObject<RecipeSerializer<CircuitFabricatorRecipe>> CIRCUIT_FABRICATOR_SERIALIZER =
                RECIPE_SERIALIZERS.register("circuit_fabricator", () -> CircuitFabricatorRecipe.Serializer.INSTANCE);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TestMod.MOD_ID);

    public static final RegistryObject<RecipeType<CircuitFabricatorRecipe>> CIRCUIT_FABRICATOR_TYPE =
            RECIPE_TYPES.register("circuit_fabricator", () -> CircuitFabricatorRecipe.Type.INSTANCE);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}
