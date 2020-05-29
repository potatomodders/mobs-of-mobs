package potatocult.mobsofmobs.core;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
    }

    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }
}
