package com.crispy.mobs_of_mobs.procedures;

import net.minecraft.entity.Entity;

import com.crispy.mobs_of_mobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class PyromancerPlayerCollidesWithThisEntityProcedure extends MobsofMobsElements.ModElement {
	public PyromancerPlayerCollidesWithThisEntityProcedure(MobsofMobsElements instance) {
		super(instance, 11);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PyromancerPlayerCollidesWithThisEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setFire((int) 2);
	}
}
