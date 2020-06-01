package potatocult.mobsofmobs.group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.items.ItemHolder;

public class CreativeTab extends ItemGroup {
    public CreativeTab() {
        super(MobsOfMobs.MODID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemHolder.MITHRIL_INGOT);
    }

    @Override
    public boolean hasSearchBar() {
        return false;
    }

    public int getSearchbarWidth() {
        return 80;
    }

    /* public net.minecraft.util.ResourceLocation getBackgroundImage() {
        return new net.minecraft.util.ResourceLocation("mobsofmobs:textures/gui/container/creative_inventory/tab_mobsofmobs.png");
    };

    public net.minecraft.util.ResourceLocation getTabsImage() {
        return new net.minecraft.util.ResourceLocation("mobsofmobs:textures/gui/container/creative_inventory/tabs.png");
    };
    */
}
