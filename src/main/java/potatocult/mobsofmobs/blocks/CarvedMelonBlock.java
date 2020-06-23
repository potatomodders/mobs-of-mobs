package potatocult.mobsofmobs.blocks;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockMaterialMatcher;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import potatocult.mobsofmobs.entities.passive.GoldGolemEntity;
import potatocult.mobsofmobs.registry.ModEntityTypes;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class CarvedMelonBlock extends HorizontalBlock {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    @Nullable
    private BlockPattern snowmanBasePattern;
    @Nullable
    private BlockPattern snowmanPattern;
    @Nullable
    private BlockPattern golemBasePattern;
    @Nullable
    private BlockPattern golemPattern;
    private static final Predicate<BlockState> IS_MELON = (p_210301_0_) -> {
        return p_210301_0_ != null && (p_210301_0_.getBlock() == BlockHolder.CARVED_MELON || p_210301_0_.getBlock() == BlockHolder.JACK_O_MELON);
    };

    public CarvedMelonBlock(Block.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (oldState.getBlock() != state.getBlock()) {
            this.trySpawnGolem(worldIn, pos);
        }
    }

    public boolean canDispenserPlace(IWorldReader p_196354_1_, BlockPos p_196354_2_) {
        return this.getGolemBasePattern().match(p_196354_1_, p_196354_2_) != null;
    }

    private void trySpawnGolem(World p_196358_1_, BlockPos p_196358_2_) {
        BlockPattern.PatternHelper blockpattern$patternhelper = this.getGolemPattern().match(p_196358_1_, p_196358_2_);
        if (blockpattern$patternhelper != null) {
            blockpattern$patternhelper = this.getGolemPattern().match(p_196358_1_, p_196358_2_);
            if (blockpattern$patternhelper != null) {
                for(int j = 0; j < this.getGolemPattern().getPalmLength(); ++j) {
                    for(int k = 0; k < this.getGolemPattern().getThumbLength(); ++k) {
                        CachedBlockInfo cachedblockinfo2 = blockpattern$patternhelper.translateOffset(j, k, 0);
                        p_196358_1_.setBlockState(cachedblockinfo2.getPos(), Blocks.AIR.getDefaultState(), 2);
                        p_196358_1_.playEvent(2001, cachedblockinfo2.getPos(), Block.getStateId(cachedblockinfo2.getBlockState()));
                    }
                }

                BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
                GoldGolemEntity goldgolementity = ModEntityTypes.GOLD_GOLEM_ENTITY.create(p_196358_1_);
                goldgolementity.setPlayerCreated(true);
                goldgolementity.setLocationAndAngles((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.05D, (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
                p_196358_1_.addEntity(goldgolementity);

                for(ServerPlayerEntity serverplayerentity1 : p_196358_1_.getEntitiesWithinAABB(ServerPlayerEntity.class, goldgolementity.getBoundingBox().grow(5.0D))) {
                    CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity1, goldgolementity);
                }

                for(int i1 = 0; i1 < this.getGolemPattern().getPalmLength(); ++i1) {
                    for(int j1 = 0; j1 < this.getGolemPattern().getThumbLength(); ++j1) {
                        CachedBlockInfo cachedblockinfo1 = blockpattern$patternhelper.translateOffset(i1, j1, 0);
                        p_196358_1_.notifyNeighbors(cachedblockinfo1.getPos(), Blocks.AIR);
                    }
                }
            }
        }

    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }


    private BlockPattern getGolemBasePattern() {
        if (this.golemBasePattern == null) {
            this.golemBasePattern = BlockPatternBuilder.start().aisle("~ ~", "###", "~#~").where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.GOLD_BLOCK))).where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.golemBasePattern;
    }

    private BlockPattern getGolemPattern() {
        if (this.golemPattern == null) {
            this.golemPattern = BlockPatternBuilder.start().aisle("~^~", "###", "~#~").where('^', CachedBlockInfo.hasState(IS_MELON)).where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.GOLD_BLOCK))).where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.golemPattern;
    }

    public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
        return true;
    }
}