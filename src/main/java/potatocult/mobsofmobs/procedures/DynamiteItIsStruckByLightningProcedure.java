package potatocult.mobsofmobs.procedures;

import potatocult.mobsofmobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class DynamiteItIsStruckByLightningProcedure extends MobsofMobsElements.ModElement {
	public DynamiteItIsStruckByLightningProcedure(MobsofMobsElements instance) {
		super(instance, 35);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure DynamiteItIsStruckByLightning!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).setHealth((float) 0);
	}
}
