package net.ewan.testmod.block;

import net.ewan.testmod.block.entity.CircuitFabricatorBlockEntity;
import net.ewan.testmod.block.entity.ModBlockEntities;
import net.ewan.testmod.block.entity.RocketWorkbenchEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class CircuitFabricatorBlock extends BaseEntityBlock {
    protected CircuitFabricatorBlock(Properties properties) {
        super(properties);
    }


    /* Block Entity */

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState Newstate, boolean IsMoving) {

        if (state.getBlock() != Newstate.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof CircuitFabricatorBlockEntity) {
                ((CircuitFabricatorBlockEntity) blockEntity).drops();
            }
        }

        super.onRemove(state, level, pos, Newstate, IsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof CircuitFabricatorBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer)pPlayer), (CircuitFabricatorBlockEntity)entity, pPos);
            }else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CircuitFabricatorBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.CIRCUIT_FABRICATOR.get(),
                CircuitFabricatorBlockEntity::tick);
    }
}
