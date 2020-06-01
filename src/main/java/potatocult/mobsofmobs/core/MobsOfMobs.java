package potatocult.mobsofmobs.core;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import potatocult.mobsofmobs.group.CreativeTab;

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
        INSTANCE = this;
        LOGGER.debug("Mobs Of Mobs Loaded!");

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::OnLoadComplete);

        MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }

    private void OnLoadComplete(final FMLLoadCompleteEvent event) {

    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(MobsOfMobs.MODID, name);
    }

    public static Rarity getRarity() {
        return rarity != null ? rarity : Rarity.EPIC;
    }
}

