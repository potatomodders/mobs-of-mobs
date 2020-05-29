package potatocult.mobsofmobs.core;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MobsOfMobs.MODID)
public class MobsOfMobs
{

    public static final String MODID = "mobs-of-mobs";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public MobsOfMobs() {
        LOGGER.debug("Mobs Of Mobs Loaded!");
    }
}
