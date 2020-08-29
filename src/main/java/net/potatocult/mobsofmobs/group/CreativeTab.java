package net.potatocult.mobsofmobs.group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.potatocult.mobsofmobs.items.ItemHolder;
import net.potatocult.mobsofmobs.blocks.BlockHolder;
import net.potatocult.mobsofmobs.core.MobsOfMobs;

import java.util.Random;

public class CreativeTab extends ItemGroup {
    protected final Random rand = new Random();

    public CreativeTab() {
        super(MobsOfMobs.MODID);
    }

    @Override
    public ItemStack createIcon() {
        if (this.rand.nextInt(2) == 0) {
            return new ItemStack(ItemHolder.PENGUIN_FEATHER);
        } else if (this.rand.nextInt(2) == 0) {
            return new ItemStack(ItemHolder.MITHRIL_INGOT);
        } else {
            return new ItemStack(BlockHolder.CARVED_MELON);
        }
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