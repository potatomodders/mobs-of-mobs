package potatocult.mobsofmobs.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;
import potatocult.mobsofmobs.core.MobsOfMobs;

import static potatocult.mobsofmobs.util.InjectionUtil.Null;

@ObjectHolder(MobsOfMobs.MODID)
public class BlockHolder {
    @ObjectHolder("carved_melon")
    public static final Block CARVED_MELON = Null();
    @ObjectHolder("jack_o_melon")
    public static final Block JACK_O_MELON = Null();
}
