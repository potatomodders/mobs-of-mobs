package net.potatocult.mobsofmobs.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.potatocult.mobsofmobs.client.entity.render.GoldGolemRenderer;
import net.potatocult.mobsofmobs.client.entity.render.PenguinRenderer;
import net.potatocult.mobsofmobs.client.entity.render.PyromancerRenderer;
import net.potatocult.mobsofmobs.client.entity.render.WightRenderer;
import net.potatocult.mobsofmobs.core.MobsOfMobs;
import net.potatocult.mobsofmobs.registry.ModEntityTypes;

@EventBusSubscriber(modid = MobsOfMobs.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GOLD_GOLEM_ENTITY.get(), GoldGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PENGUIN_ENTITY.get(), PenguinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PYROMANCER_ENTITY.get(), PyromancerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WIGHT_ENTITY.get(), WightRenderer::new);
    }

}