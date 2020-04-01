package potatocult.mobsofmobs.procedures;

import potatocult.mobsofmobs.item.FlameEssenceItem;
import potatocult.mobsofmobs.item.BootsOfFireItem;
import potatocult.mobsofmobs.MobsofMobsElements;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;

@MobsofMobsElements.ModElement.Tag
public class PyromancerEntityDiesProcedure extends MobsofMobsElements.ModElement {
	public PyromancerEntityDiesProcedure(MobsofMobsElements instance) {
		super(instance, 34);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PyromancerEntityDies!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PyromancerEntityDies!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PyromancerEntityDies!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PyromancerEntityDies!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		for (int index0 = 0; index0 < (int) ((((new java.util.Random()).nextInt((int) 10 + 1)) + 1)); index0++) {
			if (!world.isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(FlameEssenceItem.block, (int) (1)));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
		}
		if (!world.isRemote) {
			ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(BootsOfFireItem.boots, (int) (1)));
			entityToSpawn.setPickupDelay(10);
			world.addEntity(entityToSpawn);
		}
	}
}
