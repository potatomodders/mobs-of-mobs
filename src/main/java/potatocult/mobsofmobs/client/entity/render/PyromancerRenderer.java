package potatocult.mobsofmobs.client.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import potatocult.mobsofmobs.client.entity.model.PyromancerModel;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.entities.boss.PyromancerEntity;

public class PyromancerRenderer extends MobRenderer<PyromancerEntity, PyromancerModel<PyromancerEntity>> {

    protected static final ResourceLocation PYROMANCER_TEXTURE = new ResourceLocation(MobsOfMobs.MODID,
            "textures/entity/pyromancer.png");

    public PyromancerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PyromancerModel<PyromancerEntity>(0.5F, true), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(PyromancerEntity entity) {
        return PYROMANCER_TEXTURE;
    }
}
