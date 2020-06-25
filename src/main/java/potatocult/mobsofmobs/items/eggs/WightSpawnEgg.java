package potatocult.mobsofmobs.items.eggs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import potatocult.mobsofmobs.registry.ModEntityTypes;

public class WightSpawnEgg extends Item {

    private final int primaryColor;
    private final int secondaryColor;

    public WightSpawnEgg(Properties properties, int primaryColorIn, int secondaryColorIn) {
        super(properties);
        this.primaryColor = primaryColorIn;
        this.secondaryColor = secondaryColorIn;
    }

    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (world.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            ItemStack itemstack = context.getItem();
            BlockPos blockpos = context.getPos();
            Direction direction = context.getFace();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            if (block == Blocks.SPAWNER) {
                TileEntity tileentity = world.getTileEntity(blockpos);
                if (tileentity instanceof MobSpawnerTileEntity) {
                    AbstractSpawner abstractspawner = ((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic();
                    abstractspawner.setEntityType(ModEntityTypes.WIGHT_ENTITY);
                    tileentity.markDirty();
                    world.notifyBlockUpdate(blockpos, blockstate, blockstate, 3);
                    itemstack.shrink(1);
                    return ActionResultType.SUCCESS;
                }
            }

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.offset(direction);
            }

            if (ModEntityTypes.WIGHT_ENTITY.spawn(world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, false, false) == null) {
                itemstack.shrink(1);
            }

            return ActionResultType.SUCCESS;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
    }
}