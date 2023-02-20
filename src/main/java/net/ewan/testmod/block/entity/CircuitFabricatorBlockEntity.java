package net.ewan.testmod.block.entity;

import net.ewan.testmod.item.ModItems;
import net.ewan.testmod.recipe.CircuitFabricatorRecipe;
import net.ewan.testmod.screen.CircuitFabricatorMenu;
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
import net.minecraft.world.item.Items;
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

import javax.swing.text.html.Option;
import java.util.Optional;

public class CircuitFabricatorBlockEntity extends BlockEntity implements MenuProvider {
    public static int x;
    public static int y;
    public static int z;
    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;


    public CircuitFabricatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CIRCUIT_FABRICATOR.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CircuitFabricatorBlockEntity.this.progress;
                    case 1 -> CircuitFabricatorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CircuitFabricatorBlockEntity.this.progress = value;
                    case 1 -> CircuitFabricatorBlockEntity.this.maxProgress = value;
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
        return Component.literal("");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
        return new CircuitFabricatorMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return  lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }


    @Override
    protected void saveAdditional(CompoundTag nbt) {

        nbt.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CircuitFabricatorBlockEntity entity) {

        if(level.isClientSide()) {
            return;
        }

        if (hasRecipe(entity)) {
            entity.progress++;
            setChanged(level, pos, state);

            if (entity.progress >= entity.maxProgress) {
                craftItem(entity);
            } else {
                entity.resetProgress();
                setChanged(level,pos,state);
            }
        }



    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(CircuitFabricatorBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<CircuitFabricatorRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(CircuitFabricatorRecipe.Type.INSTANCE, inventory, level);

        if (hasRecipe(entity)) {
            entity.itemHandler.extractItem(0,1,false);
            entity.itemHandler.extractItem(1,1,false);
            entity.itemHandler.extractItem(2,1,false);
            entity.itemHandler.extractItem(3,1,false);

            entity.itemHandler.setStackInSlot(4,new ItemStack(recipe.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(4).getCount() + 1));
        }
    }

    private static boolean hasRecipe(CircuitFabricatorBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<CircuitFabricatorRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(CircuitFabricatorRecipe.Type.INSTANCE,inventory,level);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemAmountIntoOutputSlot(inventory, recipe.get().getResultItem());
    }

    private static boolean canInsertItemAmountIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(4).getItem() == itemStack.getItem() || inventory.getItem(4).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(4).getMaxStackSize() > inventory.getItem(4).getCount();
    }
}
