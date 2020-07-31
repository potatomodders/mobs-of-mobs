package potatocult.mobsofmobs.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.entities.boss.PyromancerEntity;
import potatocult.mobsofmobs.entities.monster.WightEntity;
import potatocult.mobsofmobs.entities.passive.GoldGolemEntity;
import potatocult.mobsofmobs.entities.passive.PenguinEntity;

import java.util.List;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            MobsOfMobs.MODID);

    public static final RegistryObject<EntityType<GoldGolemEntity>> GOLD_GOLEM_ENTITY = ENTITY_TYPES.register("gold_golem",
            () -> EntityType.Builder.create(GoldGolemEntity::new, EntityClassification.MISC).size(1.4F, 2.7F)
                    .immuneToFire().build(new ResourceLocation(MobsOfMobs.MODID, "gold_golem").toString()));


    public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN_ENTITY = ENTITY_TYPES.register("penguin",
            () -> EntityType.Builder.create(PenguinEntity::new, EntityClassification.CREATURE).size(0.6F, 1.5F)
                    .build(new ResourceLocation(MobsOfMobs.MODID, "penguin").toString()));


    public static final RegistryObject<EntityType<PyromancerEntity>> PYROMANCER_ENTITY = ENTITY_TYPES.register("pyromancer",
            () -> EntityType.Builder.create(PyromancerEntity::new, EntityClassification.MONSTER).size(0.6F, 1.95F)
                    .immuneToFire().build(new ResourceLocation(MobsOfMobs.MODID, "pyromancer").toString()));


    public static final RegistryObject<EntityType<WightEntity>> WIGHT_ENTITY = ENTITY_TYPES.register("wight",
            () -> EntityType.Builder.create(WightEntity::new, EntityClassification.MONSTER).size(0.6F, 1.95F)
                    .build(new ResourceLocation(MobsOfMobs.MODID, "wight").toString()));
}

