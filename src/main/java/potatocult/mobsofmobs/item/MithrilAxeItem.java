
package potatocult.mobsofmobs.item;

import potatocult.mobsofmobs.itemgroup.MobsOfMobsStuffItemGroup;
import potatocult.mobsofmobs.MobsofMobsElements;

import org.omg.CORBA.ObjectHolder;

@MobsofMobsElements.ModElement.Tag
public class MithrilAxeItem extends MobsofMobsElements.ModElement {
	@ObjectHolder("mobs_of_mobs:mithrilaxe")
	public static final Item block = null;
	public MithrilAxeItem(MobsofMobsElements instance) {
		super(instance, 14);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 1164;
			}

			public float getEfficiency() {
				return 12f;
			}

			public float getAttackDamage() {
				return 16f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 42;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}
		}, 1, -3f, new Item.Properties().group(MobsOfMobsStuffItemGroup.tab)) {
		}.setRegistryName("mithrilaxe"));
	}
}
