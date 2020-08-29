package net.potatocult.mobsofmobs.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.potatocult.mobsofmobs.client.entity.render.layers.GoldGolemCracksLayer;
import net.potatocult.mobsofmobs.client.entity.model.GoldGolemModel;
import net.potatocult.mobsofmobs.core.MobsOfMobs;
import net.potatocult.mobsofmobs.entities.passive.GoldGolemEntity;

@OnlyIn(Dist.CLIENT)
public class GoldGolemRenderer extends MobRenderer<GoldGolemEntity, GoldGolemModel<GoldGolemEntity>> {
    protected static final ResourceLocation GOLD_GOLEM_TEXTURE = new ResourceLocation(MobsOfMobs.MODID,
            "textures/entity/gold_golem/gold_golem.png");

    public GoldGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GoldGolemModel<>(), 0.7F);
        this.addLayer(new GoldGolemCracksLayer(this));
    }

    public ResourceLocation getEntityTexture(GoldGolemEntity entity) {
        return GOLD_GOLEM_TEXTURE;
    }

    protected void applyRotations(GoldGolemEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double) entityLiving.limbSwingAmount < 0.01D)) {
            float f = 13.0F;
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}