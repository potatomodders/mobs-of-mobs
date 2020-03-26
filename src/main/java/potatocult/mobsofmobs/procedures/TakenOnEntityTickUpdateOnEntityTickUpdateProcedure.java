package potatocult.mobsofmobs.procedures;

import potatocult.mobsofmobs.MobsofMobsElements;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

@MobsofMobsElements.ModElement.Tag
public class TakenOnEntityTickUpdateOnEntityTickUpdateProcedure extends MobsofMobsElements.ModElement {
	public TakenOnEntityTickUpdateOnEntityTickUpdateProcedure(MobsofMobsElements instance) {
		super(instance, 14);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure TakenOnEntityTickUpdateOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure TakenOnEntityTickUpdateOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure TakenOnEntityTickUpdateOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure TakenOnEntityTickUpdateOnEntityTickUpdate!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.TORCH.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.WALL_TORCH.getDefaultState().getBlock()))) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
		}
	}
}
