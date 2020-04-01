
package potatocult.mobsofmobs.itemgroup;

import potatocult.mobsofmobs.block.PyroForgeBlock;
import potatocult.mobsofmobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class MobsOfMobsStuffItemGroup extends MobsofMobsElements.ModElement {
	public MobsOfMobsStuffItemGroup(MobsofMobsElements instance) {
		super(instance, 32);
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
