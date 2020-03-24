
package com.crispy.mobs_of_mobs.item;

import org.omg.CORBA.ObjectHolder;

import com.crispy.mobs_of_mobs.itemgroup.MobsOfMobsStuffItemGroup;
import com.crispy.mobs_of_mobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class MithrilPickaxeItem extends MobsofMobsElements.ModElement {
	@ObjectHolder("mobs_of_mobs:mithrilpickaxe")
	public static final Item block = null;
	public MithrilPickaxeItem(MobsofMobsElements instance) {
		super(instance, 10);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 1164;
			}

			public float getEfficiency() {
				return 12f;
			}

			public float getAttackDamage() {
				return 4f;
			}

			public int getHarvestLevel() {
				return 6;
			}

			public int getEnchantability() {
				return 42;
			}

			public Ingredient getRepairMaterial() {
				return null;
			}
		}, 1, -3f, new Item.Properties().group(MobsOfMobsStuffItemGroup.tab)) {
		}.setRegistryName("mithrilpickaxe"));
	}
}
