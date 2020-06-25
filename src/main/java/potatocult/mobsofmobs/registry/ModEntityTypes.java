package potatocult.mobsofmobs.registry;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.entities.boss.PyromancerEntity;
import potatocult.mobsofmobs.entities.monster.WightEntity;
import potatocult.mobsofmobs.entities.passive.GoldGolemEntity;
import potatocult.mobsofmobs.entities.passive.PenguinEntity;

import java.util.List;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MobsOfMobs.MODID);
    public static final EntityType<GoldGolemEntity> GOLD_GOLEM_ENTITY = register(new ResourceLocation(MobsOfMobs.MODID, "gold_golem").toString(), EntityType.Builder.create(GoldGolemEntity::new, EntityClassification.MISC).size(1.4F, 2.7F));
    public static final EntityType<PenguinEntity> PENGUIN_ENTITY = register(new ResourceLocation(MobsOfMobs.MODID, "penguin").toString(), EntityType.Builder.create(PenguinEntity::new, EntityClassification.CREATURE).size(0.6F, 1.5F));
    public static final EntityType<PyromancerEntity> PYROMANCER_ENTITY = register(new ResourceLocation(MobsOfMobs.MODID, "pyromancer").toString(), EntityType.Builder.create(PyromancerEntity::new, EntityClassification.MONSTER).size(0.6F, 1.95F));
    public static final EntityType<WightEntity> WIGHT_ENTITY = register(new ResourceLocation(MobsOfMobs.MODID, "wight").toString(), EntityType.Builder.create(WightEntity::new, EntityClassification.MONSTER).size(0.6F, 1.95F));
    private static final List<EntityType<?>> entities = Lists.newArrayList();
    private static final List<EntityType<?>> mobs = Lists.newArrayList();

    private static <T extends Entity> EntityType<T> register(String key, EntityType.Builder<T> builder) {
        return Registry.register(Registry.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerEntityWorldSpawns() {
        registerEntityWorldSpawn(GOLD_GOLEM_ENTITY, 10, 4, 11);
        registerEntityWorldSpawn(PENGUIN_ENTITY, 21, 2, 5, Biomes.SNOWY_TUNDRA, Biomes.ICE_SPIKES, Biomes.FROZEN_RIVER, Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
        registerEntityWorldSpawn(PYROMANCER_ENTITY, 1, 1, 1, Biomes.NETHER);
        registerEntityWorldSpawn(WIGHT_ENTITY, 10, 4, 11, Biomes.PLAINS);
    }

    public static void registerEntityWorldSpawn(EntityType<?> entity, int weight, int minGroup, int maxGroup, Biome... biomes) {
        for (Biome biome : biomes) {
            if (biome != null) {
                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroup, maxGroup));
            }
        }
    }
}