
package potatocult.mobsofmobs.item;

import potatocult.mobsofmobs.itemgroup.MobsOfMobsStuffItemGroup;
import potatocult.mobsofmobs.MobsofMobsElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

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
