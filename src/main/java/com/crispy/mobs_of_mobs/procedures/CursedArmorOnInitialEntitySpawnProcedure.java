package com.crispy.mobs_of_mobs.procedures;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import com.crispy.mobs_of_mobs.entity.CursedArmorWithSwordEntity;
import com.crispy.mobs_of_mobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class CursedArmorOnInitialEntitySpawnProcedure extends MobsofMobsElements.ModElement {
	public CursedArmorOnInitialEntitySpawnProcedure(MobsofMobsElements instance) {
		super(instance, 16);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure CursedArmorOnInitialEntitySpawn!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure CursedArmorOnInitialEntitySpawn!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure CursedArmorOnInitialEntitySpawn!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure CursedArmorOnInitialEntitySpawn!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure CursedArmorOnInitialEntitySpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((Math.random() < 0.3)) {
			if (!world.isRemote) {
				Entity entityToSpawn = new CursedArmorWithSwordEntity.CustomEntity(CursedArmorWithSwordEntity.entity, world);
				entityToSpawn.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360F, 0);
				world.addEntity(entityToSpawn);
			}
			entity.remove();
		}
	}
}
