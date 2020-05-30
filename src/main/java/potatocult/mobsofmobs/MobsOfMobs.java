package potatocult.mobsofmobs;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MobsOfMobs.MODID)
public class MobsOfMobs
{

    public static final String MODID = "mobs-of-mobs";
    public static final String MODNAME = "Mobs Of Mobs";
    public static final String VERSION = "1.0.0";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static MobsOfMobs INSTANCE;

    public MobsOfMobs() {
        INSTANCE = this;
        LOGGER.debug("Mobs Of Mobs Loaded!");

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::OnLoadComplete);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }

    private void OnLoadComplete(final FMLLoadCompleteEvent event) {

    }
}

