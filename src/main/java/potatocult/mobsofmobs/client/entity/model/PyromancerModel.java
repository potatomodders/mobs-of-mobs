package potatocult.mobsofmobs.client.entity.model;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import potatocult.mobsofmobs.entities.boss.PyromancerEntity;

@OnlyIn(Dist.CLIENT)
public class PyromancerModel<T extends PyromancerEntity> extends AbstractPyromancerModel<T> {
     public PyromancerModel(float modelSize, boolean p_i1168_2_) {
         this(modelSize, 0.0F, 64, p_i1168_2_ ? 64 : 64);
     }

     protected PyromancerModel(float p_i48914_1_, float p_i48914_2_, int p_i48914_3_, int p_i48914_4_) {
         super(p_i48914_1_, p_i48914_2_, p_i48914_3_, p_i48914_4_);
     }
}