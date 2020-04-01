package potatocult.mobsofmobs.procedures;

import potatocult.mobsofmobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class FireBallWandMobIsHitWithToolProcedure extends MobsofMobsElements.ModElement {
	public FireBallWandMobIsHitWithToolProcedure(MobsofMobsElements instance) {
		super(instance, 39);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure FireBallWandMobIsHitWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setFire((int) 3);
	}
}
