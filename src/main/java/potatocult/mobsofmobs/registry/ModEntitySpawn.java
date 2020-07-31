package potatocult.mobsofmobs.registry;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import potatocult.mobsofmobs.entities.boss.PyromancerEntity;
import potatocult.mobsofmobs.entities.monster.WightEntity;
import potatocult.mobsofmobs.entities.passive.GoldGolemEntity;
import potatocult.mobsofmobs.entities.passive.PenguinEntity;

public class ModEntitySpawn {

    public static void registerEntityWorldSpawns() {
        registerEntityWorldSpawn(ModEntityTypes.GOLD_GOLEM_ENTITY.get(), 10, 4, 11);
        registerEntityWorldSpawn(ModEntityTypes.PENGUIN_ENTITY.get(), 21, 2, 5, Biomes.SNOWY_TUNDRA, Biomes.ICE_SPIKES, Biomes.FROZEN_RIVER, Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
        registerEntityWorldSpawn(ModEntityTypes.PYROMANCER_ENTITY.get(), 1, 1, 1, Biomes.NETHER);
        registerEntityWorldSpawn(ModEntityTypes.WIGHT_ENTITY.get(), 10, 4, 11, Biomes.PLAINS);
    }

    public static void EntitySpawnPlacementRegistry() {
        EntitySpawnPlacementRegistry.register(ModEntityTypes.GOLD_GOLEM_ENTITY.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                GoldGolemEntity::spawning);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.PENGUIN_ENTITY.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                PenguinEntity::spawning);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.PYROMANCER_ENTITY.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                PyromancerEntity::spawning);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.WIGHT_ENTITY.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                WightEntity::spawning);
    }

    public static void registerEntityWorldSpawn(EntityType<?> entity, int weight, int minGroup, int maxGroup,
                                                Biome... biomes) {
        for (Biome biome : biomes) {
            if (biome != null) {
                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroup, maxGroup));
            }
        }
    }
}