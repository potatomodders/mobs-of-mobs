
package com.crispy.mobs_of_mobs.itemgroup;

import com.crispy.mobs_of_mobs.block.PyroForgeBlock;
import com.crispy.mobs_of_mobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class MobsOfMobsStuffItemGroup extends MobsofMobsElements.ModElement {
	public MobsOfMobsStuffItemGroup(MobsofMobsElements instance) {
		super(instance, 26);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmobsofmobsstuff") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(PyroForgeBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
