package potatocult.mobsofmobs.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import potatocult.mobsofmobs.core.MobsOfMobs;

public class ModTags {
    {
/*
        public static class Blocks
        {
            public static final Tag<Block> CHESTS = tag("chests");

            private static Tag<Block> tag(String name)
            {
                return new BlockTags.Wrapper(new ResourceLocation(MobsOfMobs.MODID, name));
            }
        }
*/

    }
        public static class Items
        {
            public static final Tag<Item> PENGUIN_TEMPTATION = tag("penguintemptation");
            public static final Tag<Item> PENGUIN_DEADLY = tag("penguindeadly");

            private static Tag<Item> tag(String name)
            {
                return new ItemTags.Wrapper(new ResourceLocation(MobsOfMobs.MODID, name));
            }
        }

    }
