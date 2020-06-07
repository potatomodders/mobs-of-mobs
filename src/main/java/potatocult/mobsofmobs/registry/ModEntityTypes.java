package potatocult.mobsofmobs.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.entities.PenguinEntity;
import potatocult.mobsofmobs.entities.boss.PyromancerEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MobsOfMobs.MODID);

    public static final RegistryObject<EntityType<PyromancerEntity>> PYROMANCER_ENTITY = ENTITY_TYPES
            .register("pyromancer",
                    () -> EntityType.Builder.<PyromancerEntity>create(PyromancerEntity::new, EntityClassification.MONSTER)
                            .size(0.6f, 1.95f)
                            .build(new ResourceLocation(MobsOfMobs.MODID, "pyromancer").toString()));

    public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN_ENTITY = ENTITY_TYPES
            .register("penguin",
                    () -> EntityType.Builder.<PenguinEntity>create(PenguinEntity::new, EntityClassification.CREATURE)
                            .size(0.6f, 1.5f)
                            .build(new ResourceLocation(MobsOfMobs.MODID, "penguin").toString()));
}
