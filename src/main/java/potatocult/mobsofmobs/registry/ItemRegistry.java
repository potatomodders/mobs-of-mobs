package potatocult.mobsofmobs.registry;

import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.items.ItemTier;
import potatocult.mobsofmobs.items.eggs.GoldGolemSpawnEgg;
import potatocult.mobsofmobs.items.eggs.PenguinSpawnEgg;
import potatocult.mobsofmobs.items.eggs.PyromancerSpawnEgg;
import potatocult.mobsofmobs.items.eggs.WightSpawnEgg;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
            MobsOfMobs.MODID);

    public static final RegistryObject<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot",
            () -> new Item(new Item.Properties().group(MobsOfMobs.GROUP).maxStackSize(64).rarity(MobsOfMobs.getRarity())));

    public static final RegistryObject<SwordItem> MITHRIL_SWORD = ITEMS.register("mithril_sword",
            () -> new SwordItem(ItemTier.MITHRIL, (int) 2.5F, (float) -2.4,
                    new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())));

    public static final RegistryObject<PickaxeItem> MITHRIL_PICKAXE = ITEMS.register("mithril_pickaxe",
            () -> new PickaxeItem(ItemTier.MITHRIL, (int) 0.85F, (float) -2.8,
                    new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())));

    public static final RegistryObject<ShovelItem> MITHRIL_SHOVEL = ITEMS.register("mithril_shovel",
            () -> new ShovelItem(ItemTier.MITHRIL, (float) 0.70, (float) -3.0,
                    new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())));

    public static final RegistryObject<AxeItem> MITHRIL_AXE = ITEMS.register("mithril_axe",
            () -> new AxeItem(ItemTier.MITHRIL, (float) 3.25, (float) -3.1, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())));

    /* public static final RegistryObject<HoeItem> MITHRIL_HOE = ITEMS.register("mithril_hoe",
            () -> new HoeItem(ItemTier.MITHRIL, 5.0f, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity()));
     */

    public static final RegistryObject<ShearsItem> MITHRIL_SHEARS = ITEMS.register("mithril_shears",
            () -> new ShearsItem(new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())));


    public static final RegistryObject<Item> PENGUIN_FEATHER = ITEMS.register("penguin_feather",
            () -> new Item(new Item.Properties().group(MobsOfMobs.GROUP).maxStackSize(64).rarity(MobsOfMobs.getRarity())));

    public static final RegistryObject<Item> GOLD_GOLEM_SPAWN_EGG = ITEMS.register("gold_golem_spawn_egg",
            () -> new GoldGolemSpawnEgg(new Item.Properties().group(MobsOfMobs.GROUP).maxStackSize(64).rarity(MobsOfMobs.getRarity()), 0xffffff, 0xffffff));


    public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg",
            () -> new PenguinSpawnEgg(new Item.Properties().group(MobsOfMobs.GROUP).maxStackSize(64).rarity(MobsOfMobs.getRarity()), 0xffffff, 0xffffff));


    public static final RegistryObject<Item> PYROMANCER_SPAWN_EGG = ITEMS.register("pyromancer_spawn_egg",
            () -> new PyromancerSpawnEgg(new Item.Properties().group(MobsOfMobs.GROUP).maxStackSize(64).rarity(MobsOfMobs.getRarity()), 0xfca103, 0xfc3503));


    public static final RegistryObject<Item> WIGHT_SPAWN_EGG = ITEMS.register("wight_spawn_egg",
            () -> new WightSpawnEgg(new Item.Properties().group(MobsOfMobs.GROUP).maxStackSize(64).rarity(MobsOfMobs.getRarity()), 0xffffff, 0xffffff));



}
