package potatocult.mobsofmobs.util.registry;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import potatocult.mobsofmobs.registry.ModEntityTypes;

public class ModEntitySpawn {

    public static void registerEntityWorldSpawns() {
        registerEntityWorldSpawn(ModEntityTypes.GOLD_GOLEM_ENTITY, 10, 4, 11);
        registerEntityWorldSpawn(ModEntityTypes.PENGUIN_ENTITY, 21, 2, 5, Biomes.SNOWY_TUNDRA, Biomes.ICE_SPIKES, Biomes.FROZEN_RIVER, Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
        registerEntityWorldSpawn(ModEntityTypes.PYROMANCER_ENTITY, 1, 1, 1, Biomes.NETHER);
        registerEntityWorldSpawn(ModEntityTypes.WIGHT_ENTITY, 10, 4, 11, Biomes.PLAINS);
    }

    public static void EntitySpawnPlacementRegistry() {
        EntitySpawnPlacementRegistry.register(ModEntityTypes.GOLD_GOLEM_ENTITY,
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                CreatureEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.PENGUIN_ENTITY,
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.PYROMANCER_ENTITY,
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MonsterEntity::canMonsterSpawnInLight);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.WIGHT_ENTITY,
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MonsterEntity::canMonsterSpawnInLight);
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