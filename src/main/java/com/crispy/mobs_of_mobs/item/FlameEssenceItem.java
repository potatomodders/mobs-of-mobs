
package com.crispy.mobs_of_mobs.item;

import org.omg.CORBA.ObjectHolder;

import com.crispy.mobs_of_mobs.itemgroup.MobsOfMobsStuffItemGroup;
import com.crispy.mobs_of_mobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class FlameEssenceItem extends MobsofMobsElements.ModElement {
	@ObjectHolder("mobs_of_mobs:flameessence")
	public static final Item block = null;
	public FlameEssenceItem(MobsofMobsElements instance) {
		super(instance, 28);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MobsOfMobsStuffItemGroup.tab).maxStackSize(64));
			setRegistryName("flameessence");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}
	}
}
