package net.potatocult.mobsofmobs.client.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.potatocult.mobsofmobs.entities.passive.GoldGolemEntity;

public class GoldGolemModel<T extends GoldGolemEntity> extends SegmentedModel<T> {
    private final ModelRenderer goldGolemHead;
    private final ModelRenderer goldGolemBody;
    private final ModelRenderer goldGolemRightArm;
    private final ModelRenderer goldGolemLeftArm;
    private final ModelRenderer goldGolemLeftLeg;
    private final ModelRenderer goldGolemRightLeg;

    public GoldGolemModel() {
        int i = 128;
        int j = 128;
        this.goldGolemHead = (new ModelRenderer(this)).setTextureSize(128, 128);
        this.goldGolemHead.setRotationPoint(0.0F, -7.0F, -2.0F);
        this.goldGolemHead.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8.0F, 10.0F, 8.0F, 0.0F);
        this.goldGolemHead.setTextureOffset(24, 0).addBox(-1.0F, -5.0F, -7.5F, 2.0F, 4.0F, 2.0F, 0.0F);
        this.goldGolemBody = (new ModelRenderer(this)).setTextureSize(128, 128);
        this.goldGolemBody.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.goldGolemBody.setTextureOffset(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18.0F, 12.0F, 11.0F, 0.0F);
        this.goldGolemBody.setTextureOffset(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9.0F, 5.0F, 6.0F, 0.5F);
        this.goldGolemRightArm = (new ModelRenderer(this)).setTextureSize(128, 128);
        this.goldGolemRightArm.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.goldGolemRightArm.setTextureOffset(60, 21).addBox(-13.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F);
        this.goldGolemLeftArm = (new ModelRenderer(this)).setTextureSize(128, 128);
        this.goldGolemLeftArm.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.goldGolemLeftArm.setTextureOffset(60, 58).addBox(9.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F);
        this.goldGolemLeftLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(128, 128);
        this.goldGolemLeftLeg.setRotationPoint(-4.0F, 11.0F, 0.0F);
        this.goldGolemLeftLeg.setTextureOffset(37, 0).addBox(-3.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, 0.0F);
        this.goldGolemRightLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(128, 128);
        this.goldGolemRightLeg.mirror = true;
        this.goldGolemRightLeg.setTextureOffset(60, 0).setRotationPoint(5.0F, 11.0F, 0.0F);
        this.goldGolemRightLeg.addBox(-3.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, 0.0F);
    }

    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.goldGolemHead, this.goldGolemBody, this.goldGolemLeftLeg, this.goldGolemRightLeg, this.goldGolemRightArm, this.goldGolemLeftArm);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.goldGolemHead.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.goldGolemHead.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        this.goldGolemLeftLeg.rotateAngleX = -1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.goldGolemRightLeg.rotateAngleX = 1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.goldGolemLeftLeg.rotateAngleY = 0.0F;
        this.goldGolemRightLeg.rotateAngleY = 0.0F;
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        int i = entityIn.getAttackTimer();
        if (i > 0) {
            this.goldGolemRightArm.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float) i - partialTick, 10.0F);
            this.goldGolemLeftArm.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float) i - partialTick, 10.0F);
        } else {
            int j = entityIn.getHoldRoseTick();
            if (j > 0) {
                this.goldGolemRightArm.rotateAngleX = -0.8F + 0.025F * this.triangleWave((float) j, 70.0F);
                this.goldGolemLeftArm.rotateAngleX = 0.0F;
            } else {
                this.goldGolemRightArm.rotateAngleX = (-0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
                this.goldGolemLeftArm.rotateAngleX = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
            }
        }

    }

    private float triangleWave(float p_78172_1_, float p_78172_2_) {
        return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
    }

    public ModelRenderer getArmHoldingRose() {
        return this.goldGolemRightArm;
    }
}