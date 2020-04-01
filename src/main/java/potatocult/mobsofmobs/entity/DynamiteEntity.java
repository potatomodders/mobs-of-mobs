
package potatocult.mobsofmobs.entity;

import potatocult.mobsofmobs.procedures.DynamiteItIsStruckByLightningProcedure;
import potatocult.mobsofmobs.procedures.DynamiteEntityDiesProcedure;
import potatocult.mobsofmobs.itemgroup.MobsOfMobsStuffItemGroup;
import potatocult.mobsofmobs.MobsofMobsElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

@MobsofMobsElements.ModElement.Tag
public class DynamiteEntity extends MobsofMobsElements.ModElement {
	public static EntityType entity = null;
	public DynamiteEntity(MobsofMobsElements instance) {
		super(instance, 7);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.4f, 0.3f)).build("dynamite")
						.setRegistryName("dynamite");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -10066330, -8890311, new Item.Properties().group(MobsOfMobsStuffItemGroup.tab))
				.setRegistryName("dynamite"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("mountains")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 8, 1, 3));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelendermite(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("mobs_of_mobs:textures/newdynamite.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 10;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, CreeperEntity.class, true, true));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
			this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 2.8, true));
			this.targetSelector.addGoal(4, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(7, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.creeper.primed"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.creeper.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.creeper.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public void onStruckByLightning(LightningBoltEntity entityLightningBolt) {
			super.onStruckByLightning(entityLightningBolt);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			Entity entity = this;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				DynamiteItIsStruckByLightningProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			Entity entity = this;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				DynamiteEntityDiesProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2);
		}
	}

	// Made with Blockbench
	// Paste this code into your mod.
	// Make sure to generate all required imports
	public static class Modelendermite extends EntityModel<Entity> {
		private final RendererModel body1;
		private final RendererModel body2;
		private final RendererModel body3;
		private final RendererModel body4;
		public Modelendermite() {
			textureWidth = 64;
			textureHeight = 32;
			body1 = new RendererModel(this);
			body1.setRotationPoint(0.0F, 21.0F, -3.5F);
			body1.cubeList.add(new ModelBox(body1, 0, 0, -2.0F, 0.0F, -1.0F, 4, 3, 2, 0.0F, true));
			body2 = new RendererModel(this);
			body2.setRotationPoint(0.0F, 20.0F, 0.0F);
			body2.cubeList.add(new ModelBox(body2, 0, 5, -3.0F, 0.0F, -2.5F, 6, 4, 5, 0.0F, true));
			body3 = new RendererModel(this);
			body3.setRotationPoint(0.0F, 21.0F, 3.0F);
			body3.cubeList.add(new ModelBox(body3, 0, 14, -1.5F, 0.0F, -0.5F, 3, 3, 1, 0.0F, true));
			body4 = new RendererModel(this);
			body4.setRotationPoint(0.0F, 22.0F, 4.0F);
			body4.cubeList.add(new ModelBox(body4, 0, 18, -0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F, true));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			body1.render(f5);
			body2.render(f5);
			body3.render(f5);
			body4.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.body1.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.body1.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.body2.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.body2.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.body3.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.body3.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.body4.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.body4.rotateAngleX = f4 / (180F / (float) Math.PI);
		}
	}
}
