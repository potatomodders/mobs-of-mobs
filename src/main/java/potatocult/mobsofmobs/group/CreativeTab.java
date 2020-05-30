package potatocult.mobsofmobs.group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import potatocult.mobsofmobs.core.MobsOfMobs;

public class CreativeTab extends ItemGroup {
    public CreativeTab() {
        super(MobsOfMobs.MODID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.BLUE_WOOL.getItem());
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    public int getSearchbarWidth() {
        return 89;
    }

    /* public net.minecraft.util.ResourceLocation getBackgroundImage() {
        return new net.minecraft.util.ResourceLocation("mobsofmobs:textures/gui/container/creative_inventory/tab_extendedslabs.png");
    };

    public net.minecraft.util.ResourceLocation getTabsImage() {
        return new net.minecraft.util.ResourceLocation("mobsofmobs:textures/gui/container/creative_inventory/tabs.png");
    };
    */
}
