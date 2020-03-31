
package potatocult.mobsofmobs.item;

import potatocult.mobsofmobs.itemgroup.MobsOfMobsStuffItemGroup;
import potatocult.mobsofmobs.MobsofMobsElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

@MobsofMobsElements.ModElement.Tag
public class MithrilSwordItem extends MobsofMobsElements.ModElement {
	@ObjectHolder("mobs_of_mobs:mithrilsword")
	public static final Item block = null;
	public MithrilSwordItem(MobsofMobsElements instance) {
		super(instance, 12);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
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
				return 4;
			}

			public int getEnchantability() {
				return 42;
			}

			public Ingredient getRepairMaterial() {
				return null;
			}
		}, 3, -2.4f, new Item.Properties().group(MobsOfMobsStuffItemGroup.tab)) {
		}.setRegistryName("mithrilsword"));
	}
}
