package potatocult.mobsofmobs.registry;

import com.google.common.collect.Lists;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.entities.passive.PenguinEntity;
import potatocult.mobsofmobs.entities.boss.PyromancerEntity;
import potatocult.mobsofmobs.entities.monster.WightEntity;

import java.util.List;

public class ModEntityTypes {
    private static List<EntityType<?>> entities = Lists.newArrayList();
    private static List<EntityType<?>> mobs = Lists.newArrayList();
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MobsOfMobs.MODID);

    public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN_ENTITY = ENTITY_TYPES
            .register("penguin",
                    () -> EntityType.Builder.<PenguinEntity>create(PenguinEntity::new, EntityClassification.CREATURE)
                            .size(0.6f, 1.5f)
                            .build(new ResourceLocation(MobsOfMobs.MODID, "penguin").toString()));

    public static final RegistryObject<EntityType<PyromancerEntity>> PYROMANCER_ENTITY = ENTITY_TYPES
            .register("pyromancer",
                    () -> EntityType.Builder.<PyromancerEntity>create(PyromancerEntity::new, EntityClassification.MONSTER)
                            .size(0.6f, 1.95f)
                            .immuneToFire()
                            .build(new ResourceLocation(MobsOfMobs.MODID, "pyromancer").toString()));

    public static final RegistryObject<EntityType<WightEntity>> WIGHT_ENTITY = ENTITY_TYPES
            .register("wight",
                    () -> EntityType.Builder.<WightEntity>create(WightEntity::new, EntityClassification.MONSTER)
                            .size(0.6f, 1.95f)
                            .build(new ResourceLocation(MobsOfMobs.MODID, "wight").toString()));

    public static void registerEntityWorldSpawns() {
        registerEntityWorldSpawn(PENGUIN_ENTITY.get(), 21, 2, 5, Biomes.SNOWY_TUNDRA, Biomes.ICE_SPIKES, Biomes.FROZEN_RIVER, Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
        registerEntityWorldSpawn(PYROMANCER_ENTITY.get(), 1, 1, 1);
        registerEntityWorldSpawn(WIGHT_ENTITY.get(), 20, 4, 11);
    }

    public static void registerEntityWorldSpawn(EntityType<?> entity, int weight, int minGroup, int maxGroup, Biome... biomes) {
        for (Biome biome : biomes) {
            if (biome != null) {
                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroup, maxGroup));
            }
        }
    }
}