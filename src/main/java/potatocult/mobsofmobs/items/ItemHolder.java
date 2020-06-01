package potatocult.mobsofmobs.items;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;
import potatocult.mobsofmobs.core.MobsOfMobs;

import static potatocult.mobsofmobs.util.InjectionUtil.Null;

@ObjectHolder(MobsOfMobs.MODID)
public class ItemHolder {
    @ObjectHolder("penguin_feather")
    public static final Item PENGUIN_FEATHER = Null();
    @ObjectHolder("mithril_ingot")
    public static final Item MITHRIL_INGOT = Null();
    @ObjectHolder("mithril_sword")
    public static final Item MITHRIL_SWORD = Null();
    @ObjectHolder("mithril_pickaxe")
    public static final Item MITHRIL_PICKAXE = Null();
    @ObjectHolder("mithril_shovel")
    public static final Item MITHRIL_SHOVEL = Null();
    @ObjectHolder("mithril_axe")
    public static final Item MITHRIL_AXE = Null();
    // @ObjectHolder("mithril_hoe")
    // public static final Item MITHRIL_HOE = Null();
    @ObjectHolder("mithril_shears")
    public static final Item MITHRIL_SHEARS = Null();
}
