package potatocult.mobsofmobs.client.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import potatocult.mobsofmobs.client.entity.model.WightModel;
import potatocult.mobsofmobs.entities.monster.WightEntity;

public class WightRenderer extends AbstractWightRenderer<WightEntity, WightModel<WightEntity>> {
    public WightRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,
                new WightModel<>(0.0F, false),
                new WightModel<>(0.5F, true),
                new WightModel<>(1.0F, true));
    }
}