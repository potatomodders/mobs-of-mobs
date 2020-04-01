package potatocult.mobsofmobs.procedures;

import potatocult.mobsofmobs.MobsofMobsElements;

import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.Blocks;

@MobsofMobsElements.ModElement.Tag
public class DynamiteEntityDiesProcedure extends MobsofMobsElements.ModElement {
	public DynamiteEntityDiesProcedure(MobsofMobsElements instance) {
		super(instance, 36);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure DynamiteEntityDies!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure DynamiteEntityDies!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure DynamiteEntityDies!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure DynamiteEntityDies!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!(world.isRemote))) {
			if (!world.isRemote) {
				world.createExplosion(null, (int) x, (int) y, (int) z, (float) 1.5, Explosion.Mode.BREAK);
			}
			for (int index0 = 0; index0 < (int) ((((new java.util.Random()).nextInt((int) 4 + 1)) + 1)); index0++) {
				if (!world.isRemote) {
					ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(Items.GUNPOWDER, (int) (1)));
					entityToSpawn.setPickupDelay(10);
					world.addEntity(entityToSpawn);
				}
			}
		}
		if ((Math.random() < 0.2)) {
			if (!world.isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(Blocks.TNT, (int) (1)));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
