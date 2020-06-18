package potatocult.mobsofmobs.client.entity.render.layers;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import potatocult.mobsofmobs.client.entity.model.GoldGolemModel;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.entities.passive.GoldGolemEntity;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class GoldGolemCracksLayer extends LayerRenderer<GoldGolemEntity, GoldGolemModel<GoldGolemEntity>> {
    private static final Map<GoldGolemEntity.Cracks, ResourceLocation> field_229134_a_ = ImmutableMap.of(GoldGolemEntity.Cracks.LOW, new ResourceLocation(MobsOfMobs.MODID, "textures/entity/gold_golem/gold_golem_crackiness_low.png"), GoldGolemEntity.Cracks.MEDIUM, new ResourceLocation(MobsOfMobs.MODID, "textures/entity/gold_golem/gold_golem_crackiness_medium.png"), GoldGolemEntity.Cracks.HIGH, new ResourceLocation(MobsOfMobs.MODID, "textures/entity/gold_golem/gold_golem_crackiness_high.png"));

    public GoldGolemCracksLayer(IEntityRenderer<GoldGolemEntity, GoldGolemModel<GoldGolemEntity>> p_i226040_1_) {
        super(p_i226040_1_);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, GoldGolemEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible()) {
            GoldGolemEntity.Cracks goldgolementity$cracks = entitylivingbaseIn.func_226512_l_();
            if (goldgolementity$cracks != GoldGolemEntity.Cracks.NONE) {
                ResourceLocation resourcelocation = field_229134_a_.get(goldgolementity$cracks);
                renderCutoutModel(this.getEntityModel(), resourcelocation, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}