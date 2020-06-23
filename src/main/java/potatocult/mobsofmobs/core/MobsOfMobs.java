package potatocult.mobsofmobs.core;

import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import potatocult.mobsofmobs.group.CreativeTab;
import potatocult.mobsofmobs.registry.BlockRegistry;
import potatocult.mobsofmobs.registry.ItemRegistry;
import potatocult.mobsofmobs.registry.ModEntityTypes;

@Mod(MobsOfMobs.MODID)
public class MobsOfMobs
{

    public static final String MODID = "mobsofmobs";
    public static final String MODNAME = "Mobs Of Mobs";
    public static final String VERSION = "1.0.0";

    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final ItemGroup GROUP = new CreativeTab();

    private static final Rarity rarity = Rarity.create("MITHRIL", TextFormatting.DARK_AQUA);


    public static MobsOfMobs INSTANCE;

    public MobsOfMobs() {
        LOGGER.debug("Mobs Of Mobs Loaded!");

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);


        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);

        INSTANCE = this;
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
    }

    @SubscribeEvent
    public void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockRegistry.BLOCKS.getEntries().stream()
                .map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(MobsOfMobs.GROUP).maxStackSize(64).rarity(MobsOfMobs.getRarity());
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });

        LOGGER.debug("Registered BlockItems!");
    }

    private void setup(final FMLCommonSetupEvent event) {
        /*
        DeferredWorkQueue.runLater(ModOreGen::generateOre);
         */
        /*
         * DeferredWorkQueue.runLater(() -> { for (Biome biome : ForgeRegistries.BIOMES)
         * { if (biome instanceof ExampleBiome) {
         * biome.getSpawns(EntityClassification.MONSTER) .add(new
         * Biome.SpawnListEntry(EntityType.ZOMBIE, 1000, 1, 4)); } } });
         */
    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(MobsOfMobs.MODID, name);
    }

    public static Rarity getRarity() {
        return rarity != null ? rarity : Rarity.EPIC;
    }
}

