package potatocult.mobsofmobs.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import potatocult.mobsofmobs.entities.passive.PenguinEntity;

public class PenguinModel<T extends PenguinEntity> extends EntityModel<T> {
    private final ModelRenderer RightLeg;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer Body;
    private final ModelRenderer Head;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;

    public PenguinModel() {
        textureWidth = 64;
        textureHeight = 64;

        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
        RightLeg.setTextureOffset(10, 38).addBox(-4.0F, -1.0F, -3.0F, 3, 1, 5, 0.0F, false);
        RightLeg.setTextureOffset(36, 45).addBox(-4.0F, -2.0F, 0.0F, 3, 1, 2, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
        LeftLeg.setTextureOffset(10, 32).addBox(1.0F, -1.0F, -3.0F, 3, 1, 5, 0.0F, false);
        LeftLeg.setTextureOffset(26, 45).addBox(1.0F, -2.0F, 0.0F, 3, 1, 2, 0.0F, false);

        Body = new ModelRenderer(this);
        Body.setRotationPoint(0.0F, 24.0F, 0.0F);
        Body.setTextureOffset(0, 0).addBox(-5.0F, -15.0F, -2.0F, 10, 13, 6, 0.0F, false);
        Body.setTextureOffset(0, 19).addBox(-4.0F, -16.0F, -2.0F, 8, 1, 6, 0.0F, false);
        Body.setTextureOffset(0, 45).addBox(-2.0F, -17.0F, -1.0F, 4, 1, 4, 0.0F, false);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 24.0F, 0.0F);
        Head.setTextureOffset(32, 0).addBox(-3.0F, -23.0F, -2.0F, 6, 6, 6, 0.0F, false);
        Head.setTextureOffset(16, 45).addBox(-1.0F, -19.0F, -5.0F, 2, 1, 3, 0.0F, false);

        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-2.0F, 21.0F, 0.0F);
        setRotationAngle(RightArm, 0.0F, 0.0F, 0.1745F);
        RightArm.setTextureOffset(0, 32).addBox(-5.0F, -10.0F, -1.0F, 1, 9, 4, 0.0F, false);
        RightArm.setTextureOffset(6, 50).addBox(-5.0F, -1.0F, 0.0F, 1, 1, 2, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(3.0F, 21.0F, 0.0F);
        setRotationAngle(LeftArm, 0.0F, 0.0F, -0.1745F);
        LeftArm.setTextureOffset(28, 19).addBox(3.0F, -10.0F, -1.0F, 1, 9, 4, 0.0F, false);
        LeftArm.setTextureOffset(0, 50).addBox(3.0F, -1.0F, 0.0F, 1, 1, 2, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        RightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        LeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        RightArm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        LeftArm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
