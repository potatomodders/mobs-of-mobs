package com.crispy.mobs_of_mobs.procedures;

import net.minecraft.entity.Entity;

import com.crispy.mobs_of_mobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class TakenOnEntityTickUpdateOnInitialEntitySpawnProcedure extends MobsofMobsElements.ModElement {
	public TakenOnEntityTickUpdateOnInitialEntitySpawnProcedure(MobsofMobsElements instance) {
		super(instance, 5);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure TakenOnEntityTickUpdateOnInitialEntitySpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.posY) >= 45)) {
			entity.remove();
		}
	}
}
