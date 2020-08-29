package net.potatocult.mobsofmobs.client.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.potatocult.mobsofmobs.client.entity.model.PenguinModel;
import net.potatocult.mobsofmobs.core.MobsOfMobs;
import net.potatocult.mobsofmobs.entities.passive.PenguinEntity;

public class PenguinRenderer extends MobRenderer<PenguinEntity, PenguinModel<PenguinEntity>> {

    protected static final ResourceLocation PENGUIN_TEXTURE = new ResourceLocation(MobsOfMobs.MODID,
            "textures/entity/penguin.png");

    public PenguinRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PenguinModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(PenguinEntity entity) {
        return PENGUIN_TEXTURE;
    }
}
