
package com.crispy.mobs_of_mobs.item;

import org.omg.CORBA.ObjectHolder;

import com.crispy.mobs_of_mobs.itemgroup.MobsOfMobsStuffItemGroup;
import com.crispy.mobs_of_mobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class MithrilShovelItem extends MobsofMobsElements.ModElement {
	@ObjectHolder("mobs_of_mobs:mithrilshovel")
	public static final Item block = null;
	public MithrilShovelItem(MobsofMobsElements instance) {
		super(instance, 12);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ShovelItem(new IItemTier() {
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
		}.setRegistryName("mithrilshovel"));
	}
}
