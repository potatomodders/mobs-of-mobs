package potatocult.mobsofmobs.procedures;

import potatocult.mobsofmobs.MobsofMobsElements;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

@MobsofMobsElements.ModElement.Tag
public class FireballWandRangedItemUsedProcedure extends MobsofMobsElements.ModElement {
	public FireballWandRangedItemUsedProcedure(MobsofMobsElements instance) {
		super(instance, 43);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure FireballWandRangedItemUsed!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure FireballWandRangedItemUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).getCooldownTracker().setCooldown(itemstack.getItem(), (int) 30);
	}
}
