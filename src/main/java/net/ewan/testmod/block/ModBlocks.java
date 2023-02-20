package net.ewan.testmod.block;

import net.ewan.testmod.item.ModCreativeModeTab;
import net.ewan.testmod.item.ModItems;
import net.ewan.testmod.TestMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);


    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);


    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(1.8f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);

    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(1.8f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);

    public static final RegistryObject<Block> DEEPSLATE_ORE_TIN = registerBlock("deepslate_ore_tin",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);



    public static final RegistryObject<Block> ALUMINUM_BLOCK = registerBlock("aluminum_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(1.8f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);

    public static final RegistryObject<Block> ALUMINUM_ORE = registerBlock("aluminum_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(1.8f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);

    public static final RegistryObject<Block> DEEPSLATE_ORE_ALUMINUM = registerBlock("deepslate_ore_aluminum",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);

    public static final RegistryObject<Block> LEAD_BLOCK = registerBlock("lead_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2.1f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);


    public static final RegistryObject<Block> DESH_BLOCK = registerBlock("desh_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.4f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);

    public static final RegistryObject<Block> SILICON_BLOCK = registerBlock("silicon_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2.1f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);

    public static final RegistryObject<Block> SILICON_ORE = registerBlock("silicon_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2.1f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);

    public static final RegistryObject<Block> DEEPSLATE_ORE_SILICON = registerBlock("deepslate_ore_silicon",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops()), ModCreativeModeTab.TESTMOD_TAB);

    public static final RegistryObject<Block> ROCKET_WORKBENCH_BLOCK = registerBlock("rocket_workbench",
            () -> new RocketWorkbenchBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(4f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.TESTMOD_TAB);

    public static final RegistryObject<Block> CIRCUIT_FABRICATOR_BLOCK = registerBlock("circuit_fabricator",
            () -> new CircuitFabricatorBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(3f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.TESTMOD_TAB);




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
