package potatocult.mobsofmobs.registry;

import com.google.common.base.Preconditions;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.items.ItemTier;

import static potatocult.mobsofmobs.core.MobsOfMobs.LOGGER;

@ObjectHolder(MobsOfMobs.MODID)
public class ItemRegistry {
    @Mod.EventBusSubscriber(modid = MobsOfMobs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Register {

        @SubscribeEvent
        public static void RegisterItems(final RegistryEvent.Register<Item> event) {
            final Item[] items = {
                    new Item(new Item.Properties().group(MobsOfMobs.GROUP).maxStackSize(64).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_ingot")),
                    new SwordItem(ItemTier.MITHRIL, (int) 2.5, (float) -2.4, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_sword")),
                    new PickaxeItem(ItemTier.MITHRIL, (int) 0.85, (float) -2.8, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_pickaxe")), /* Iron */
                    new ShovelItem(ItemTier.MITHRIL, (float) 0.70, (float) -3.0, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_shovel")), /* Diamond */
                    new AxeItem(ItemTier.MITHRIL, (float) 3.25, (float) -3.1, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_axe")), /* Diamond */
                    // Wait for 1.16 // new HoeItem(ItemTier.MITHRIL, (float) -1.5, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_hoe")), /* Diamond */
                    new ShearsItem(new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_shears")),
                    new Item(new Item.Properties().group(MobsOfMobs.GROUP).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("penguin_feather"))
            };

            for (final Item item: items) {
                Preconditions.checkNotNull(item.getRegistryName(), "Block: %s has a NULL registry name", item);
                event.getRegistry().register(item);
            }
            LOGGER.debug("Registered Entities");
        }
    }
}
