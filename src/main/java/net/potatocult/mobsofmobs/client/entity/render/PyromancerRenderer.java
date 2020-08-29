package net.potatocult.mobsofmobs.client.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.potatocult.mobsofmobs.client.entity.model.PyromancerModel;
import net.potatocult.mobsofmobs.entities.boss.PyromancerEntity;

public class PyromancerRenderer extends AbstractPyromancerRenderer<PyromancerEntity, PyromancerModel<PyromancerEntity>> {
    public PyromancerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,
                new PyromancerModel<>(0.0F, false),
                new PyromancerModel<>(0.5F, true),
                new PyromancerModel<>(1.0F, true));
    }
}