package net.potatocult.mobsofmobs.common;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.potatocult.mobsofmobs.core.MobsOfMobs;

public class ModTags {
    public static class Items {
        public static final Tag<Item> PENGUIN_TEMPTATION = tag("penguintemptation");
        public static final Tag<Item> PENGUIN_DEADLY = tag("penguindeadly");
        public static final Tag<Item> PENGUIN_FEATHER = tag("penguinfeather");

        private static Tag<Item> tag(String name) {
            return new ItemTags.Wrapper(new ResourceLocation(MobsOfMobs.MODID, name));
        }
    }

}
