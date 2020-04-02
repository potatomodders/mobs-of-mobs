
package potatocult.mobsofmobs.entity;

import potatocult.mobsofmobs.itemgroup.MobsOfMobsStuffItemGroup;
import potatocult.mobsofmobs.item.PenguinFeatherItem;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
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
public class PenguinEntity extends MobsofMobsElements.ModElement {
	public static EntityType entity = null;
	public PenguinEntity(MobsofMobsElements instance) {
		super(instance, 6);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("penguin")
						.setRegistryName("penguin");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -1, -154, new Item.Properties().group(MobsOfMobsStuffItemGroup.tab)).setRegistryName("penguin"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("snowy_tundra")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("snowy_mountains")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("snowy_taiga")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("ice_spikes")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 5, 1, 2));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelpenguin(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("mobs_of_mobs:textures/enguin.png");
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
			experienceValue = 1;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, true));
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1.2));
			this.goalSelector.addGoal(4, new SwimGoal(this));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(PenguinFeatherItem.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.cat.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.puffer_fish.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2);
		}
	}

	// Paste this code into your mod.
	// Make sure to generate all required imports
	public static class Modelpenguin extends EntityModel {
		private final RendererModel RightLeg;
		private final RendererModel LeftLeg;
		private final RendererModel Body;
		private final RendererModel Head;
		private final RendererModel RightArm;
		private final RendererModel LeftArm;
		public Modelpenguin() {
			textureWidth = 64;
			textureHeight = 64;
			RightLeg = new RendererModel(this);
			RightLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
			RightLeg.cubeList.add(new ModelBox(RightLeg, 10, 38, -4.0F, -1.0F, -3.0F, 3, 1, 5, 0.0F, false));
			RightLeg.cubeList.add(new ModelBox(RightLeg, 36, 45, -4.0F, -2.0F, 0.0F, 3, 1, 2, 0.0F, false));
			LeftLeg = new RendererModel(this);
			LeftLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
			LeftLeg.cubeList.add(new ModelBox(LeftLeg, 10, 32, 1.0F, -1.0F, -3.0F, 3, 1, 5, 0.0F, false));
			LeftLeg.cubeList.add(new ModelBox(LeftLeg, 26, 45, 1.0F, -2.0F, 0.0F, 3, 1, 2, 0.0F, false));
			Body = new RendererModel(this);
			Body.setRotationPoint(0.0F, 24.0F, 0.0F);
			Body.cubeList.add(new ModelBox(Body, 0, 0, -5.0F, -15.0F, -2.0F, 10, 13, 6, 0.0F, false));
			Body.cubeList.add(new ModelBox(Body, 0, 19, -4.0F, -16.0F, -2.0F, 8, 1, 6, 0.0F, false));
			Body.cubeList.add(new ModelBox(Body, 0, 45, -2.0F, -17.0F, -1.0F, 4, 1, 4, 0.0F, false));
			Head = new RendererModel(this);
			Head.setRotationPoint(0.0F, 24.0F, 0.0F);
			Head.cubeList.add(new ModelBox(Head, 32, 0, -3.0F, -23.0F, -2.0F, 6, 6, 6, 0.0F, false));
			Head.cubeList.add(new ModelBox(Head, 16, 45, -1.0F, -19.0F, -5.0F, 2, 1, 3, 0.0F, false));
			RightArm = new RendererModel(this);
			RightArm.setRotationPoint(-2.0F, 21.0F, 0.0F);
			setRotationAngle(RightArm, 0.0F, 0.0F, 0.1745F);
			RightArm.cubeList.add(new ModelBox(RightArm, 0, 32, -5.0F, -10.0F, -1.0F, 1, 9, 4, 0.0F, false));
			RightArm.cubeList.add(new ModelBox(RightArm, 6, 50, -5.0F, -1.0F, 0.0F, 1, 1, 2, 0.0F, false));
			LeftArm = new RendererModel(this);
			LeftArm.setRotationPoint(3.0F, 21.0F, 0.0F);
			setRotationAngle(LeftArm, 0.0F, 0.0F, -0.1745F);
			LeftArm.cubeList.add(new ModelBox(LeftArm, 28, 19, 3.0F, -10.0F, -1.0F, 1, 9, 4, 0.0F, false));
			LeftArm.cubeList.add(new ModelBox(LeftArm, 0, 50, 3.0F, -1.0F, 0.0F, 1, 1, 2, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			RightLeg.render(f5);
			LeftLeg.render(f5);
			Body.render(f5);
			Head.render(f5);
			RightArm.render(f5);
			LeftArm.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
