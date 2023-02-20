package net.ewan.testmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static CreativeModeTab TESTMOD_TAB;


    public static void load() {
        TESTMOD_TAB = new CreativeModeTab("testmodtab") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.INGOT_LEAD.get());
            }
        };
    }
}
