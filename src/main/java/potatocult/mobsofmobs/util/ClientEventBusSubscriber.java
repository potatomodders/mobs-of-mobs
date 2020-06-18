package potatocult.mobsofmobs.util;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import potatocult.mobsofmobs.client.entity.render.GoldGolemRenderer;
import potatocult.mobsofmobs.client.entity.render.PenguinRenderer;
import potatocult.mobsofmobs.client.entity.render.PyromancerRenderer;
import potatocult.mobsofmobs.client.entity.render.WightRenderer;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.registry.ModEntityTypes;

@Mod.EventBusSubscriber(modid = MobsOfMobs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GOLD_GOLEM_ENTITY.get(), GoldGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PENGUIN_ENTITY.get(), PenguinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PYROMANCER_ENTITY.get(), PyromancerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WIGHT_ENTITY.get(), WightRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
                ModEntityTypes.GOLD_GOLEM_ENTITY.get(),
                ModEntityTypes.PENGUIN_ENTITY.get(),
                ModEntityTypes.PYROMANCER_ENTITY.get(),
                ModEntityTypes.WIGHT_ENTITY.get());
        ModEntityTypes.registerEntityWorldSpawns();
    }
}