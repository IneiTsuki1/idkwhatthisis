package net.ewan.testmod.block.entity;

import net.ewan.testmod.item.ModItems;
import net.ewan.testmod.screen.RocketWorkbenchMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class RocketWorkbenchEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(18) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> LazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;


    public RocketWorkbenchEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ROCKET_WORKBENCH.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> RocketWorkbenchEntity.this.progress;
                    case 1 -> RocketWorkbenchEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> RocketWorkbenchEntity.this.progress = value;
                    case 1 -> RocketWorkbenchEntity.this.maxProgress = value;
                }
            }


            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("rocket workbench");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new RocketWorkbenchMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return LazyItemHandler.cast();
        }


        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        LazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        LazyItemHandler.invalidate();
    }

    /* @Override
    protected void saveAdditional(CompoundTag p_187471_) {
        serializeNBT().put("inventory", itemHandler.serializeNBT());

    } */

    @Override
    public void load(CompoundTag p_155245_) {
        super.load(p_155245_);
        itemHandler.deserializeNBT(serializeNBT().getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i ++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RocketWorkbenchEntity pEntity) {
        if (level.isClientSide()) {
            return;
        }

        if (hasRecipe(pEntity)) {
            pEntity.progress++;
            setChanged(level, pos, state);

            if (pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }

        }else {
            pEntity.resetProgress();
            setChanged(level, pos, state);
        }

    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(RocketWorkbenchEntity pEntity) {
        if (hasRecipe(pEntity)) {
            pEntity.itemHandler.extractItem(1, 1, false);
            pEntity.itemHandler.setStackInSlot(18, new ItemStack(ModItems.INGOT_ALUMINUM.get(),
                    pEntity.itemHandler.getStackInSlot(2).getCount() + 1));

            pEntity.resetProgress();
        }
    }

    private static boolean hasRecipe(RocketWorkbenchEntity pEntity) {
        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for (int i = 0; i < pEntity.itemHandler.getSlots(); i ++) {
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
        }



        boolean hasCompressedSteelInFirstSlot = pEntity.itemHandler.getStackInSlot(0).getItem() == ModItems.FIN_ROCKET.get();
        boolean hasCompressedSteelInSecondSlot = pEntity.itemHandler.getStackInSlot(1).getItem() == ModItems.FIN_ROCKET.get();
        boolean hasCompressedSteelInFifthSlot = pEntity.itemHandler.getStackInSlot(4).getItem() == ModItems.FIN_ROCKET.get();
        boolean hasCompressedSteelInSixthSlot = pEntity.itemHandler.getStackInSlot(5).getItem() == ModItems.FIN_ROCKET.get();
        boolean hasCompressedSteelInSeventhSlot = pEntity.itemHandler.getStackInSlot(6).getItem() == ModItems.FIN_ROCKET.get();


        boolean hasCompressedSteelInThirdSlot = pEntity.itemHandler.getStackInSlot(2).getItem() == ModItems.COMPRESSED_STEEL.get();
        boolean hasCompressedSteelInFourthSlot = pEntity.itemHandler.getStackInSlot(3).getItem() == ModItems.COMPRESSED_STEEL.get();
        boolean hasCompressedSteelInEightSlot = pEntity.itemHandler.getStackInSlot(7).getItem() == ModItems.COMPRESSED_STEEL.get();
        boolean hasCompressedSteelInNinthSlot = pEntity.itemHandler.getStackInSlot(8).getItem() == ModItems.COMPRESSED_STEEL.get();
        boolean hasCompressedSteelInTenthSlot = pEntity.itemHandler.getStackInSlot(9).getItem() == ModItems.COMPRESSED_STEEL.get();
        boolean hasCompressedSteelInEleventhSlot = pEntity.itemHandler.getStackInSlot(10).getItem() == ModItems.COMPRESSED_STEEL.get();
        boolean hasCompressedSteelInTwelfthSlot = pEntity.itemHandler.getStackInSlot(11).getItem() == ModItems.COMPRESSED_STEEL.get();
        boolean hasCompressedSteelInThirteenthSlot = pEntity.itemHandler.getStackInSlot(12).getItem() == ModItems.COMPRESSED_STEEL.get();

        boolean hasCompressedSteelInFourteenthSlot = pEntity.itemHandler.getStackInSlot(13).getItem() == ModItems.NOSE_CONE.get();

        return hasCompressedSteelInFirstSlot && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemItemOutputSlot(inventory, new ItemStack(ModItems.INGOT_ALUMINUM.get(), 1));


    }

    private static boolean canInsertItemItemOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(2).getItem() == itemStack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}
