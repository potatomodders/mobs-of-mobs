package net.potatocult.mobsofmobs.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;
import net.potatocult.mobsofmobs.core.MobsOfMobs;
import net.potatocult.mobsofmobs.util.InjectionUtil;

@ObjectHolder(MobsOfMobs.MODID)
public class BlockHolder {
    @ObjectHolder("carved_melon")
    public static final Block CARVED_MELON = InjectionUtil.Null();
    @ObjectHolder("jack_o_melon")
    public static final Block JACK_O_MELON = InjectionUtil.Null();
}
