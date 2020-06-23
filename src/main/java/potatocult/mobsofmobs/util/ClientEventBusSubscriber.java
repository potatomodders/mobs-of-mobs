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
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GOLD_GOLEM_ENTITY, GoldGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PENGUIN_ENTITY, PenguinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PYROMANCER_ENTITY, PyromancerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WIGHT_ENTITY, WightRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
                ModEntityTypes.GOLD_GOLEM_ENTITY,
                ModEntityTypes.PENGUIN_ENTITY,
                ModEntityTypes.PYROMANCER_ENTITY,
                ModEntityTypes.WIGHT_ENTITY);
        ModEntityTypes.registerEntityWorldSpawns();
    }
}