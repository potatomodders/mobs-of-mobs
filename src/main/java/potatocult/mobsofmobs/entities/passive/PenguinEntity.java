package potatocult.mobsofmobs.entities.passive;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import potatocult.mobsofmobs.common.ModTags;
import potatocult.mobsofmobs.core.MobsOfMobs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class PenguinEntity extends AnimalEntity {
    ResourceLocation penguinAllergicTag = new ResourceLocation(MobsOfMobs.MODID, "penguinallergic");
    ResourceLocation penguinTemptationTag = new ResourceLocation(MobsOfMobs.MODID, "penguintemptation");
    private final Tag DEADLY_ITEM = ItemTags.getCollection().get(penguinAllergicTag);
    private final Tag BREED_ITEM = ItemTags.getCollection().get(penguinTemptationTag);

    public PenguinEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
        this.setHealth(this.getMaxHealth());
        this.experienceValue = 5;
        this.moveController = new PenguinLandMovementController(this);
        this.setPathPriority(PathNodeType.WATER, 0.2F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PenguinLandSwimGoal());
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.20D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.88D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.45D, false, Ingredient.fromTag(ModTags.Items.PENGUIN_TEMPTATION)));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.10D));
        this.goalSelector.addGoal(5, new GetOutOfWaterGoal(this));
        this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 1.00D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.00F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(8.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.38F);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.5D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PARROT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_PARROT_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PARROT_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_PARROT_STEP;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }

    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() instanceof SpawnEggItem) {
            return super.processInteract(player, hand);
        } else if (DEADLY_ITEM.contains(itemstack.getItem())) {
            if (!player.abilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            this.addPotionEffect(new EffectInstance(Effects.POISON, 900));
            if (player.isCreative() || !this.isInvulnerable()) {
                this.attackEntityFrom(DamageSource.causePlayerDamage(player), Float.MAX_VALUE);
            }

            return true;
        } else {
            return super.processInteract(player, hand);
        }
    }

    public static void playAmbientSound(World worldIn, Entity parrotIn) {
        if (!parrotIn.isSilent() && worldIn.rand.nextInt(200) == 0) {
            worldIn.playSound((PlayerEntity)null, parrotIn.getPosX(), parrotIn.getPosY(), parrotIn.getPosZ(), getAmbientSound(worldIn.rand), parrotIn.getSoundCategory(), 1.0F, getPitch(worldIn.rand));
        }

    }

    private static float getPitch(Random rand) {
        return 0;
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
    }

    private static SoundEvent getAmbientSound(Random random) {
        if (random.nextInt(1000) == 0) {
            return SoundEvents.ENTITY_PARROT_AMBIENT;
        }
        return null;
    }

    public boolean isBreedingItem(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        return BREED_ITEM.contains(itemstack.getItem());
    }

    @Override
    @Nullable
    public AgeableEntity createChild(AgeableEntity ageable) {
        return (AgeableEntity) this.getType().create(this.world);
    }

        @Override
        public boolean canBreatheUnderwater() {
            return true;
        }

        @Override
        protected float getWaterSlowDown() {
            return 0.0F;
        }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return this.isChild() ? size.height * 0.75F : size.height * 0.7F;
    }

    public class PenguinLandSwimGoal extends SwimGoal {

        public PenguinLandSwimGoal() {
            super(PenguinEntity.this);
        }

        @Override
        public boolean shouldExecute() {
            return PenguinEntity.this.isInWater() && PenguinEntity.this.getSubmergedHeight() > 1.45D * 0.55D || PenguinEntity.this.isInLava();
        }
    }

    static class PenguinLandMovementController extends MovementController {
        private final PenguinEntity penguin;

        PenguinLandMovementController(PenguinEntity penguin) {
            super(penguin);
            this.penguin = penguin;
        }

        @Override
        public void tick() {
            super.tick();
            this.updateSpeed();
        }

        private void updateSpeed() {
            if (this.penguin.isInWater()) {
                if (this.penguin.isChild()) {
                    this.penguin.setAIMoveSpeed(0.2F);
                } else {
                    this.penguin.setAIMoveSpeed(0.18F);
                }
            } else if (this.penguin.onGround) {
                this.penguin.setAIMoveSpeed(0.1F);
            }
        }
    }

    static class GetOutOfWaterGoal extends MoveToBlockGoal {
        private final PenguinEntity penguin;

        private GetOutOfWaterGoal(PenguinEntity penguin) {
            super(penguin, penguin.isChild() ? 1.4D : 1.2D, 24);
            this.penguin = penguin;
            this.field_203112_e = -1;
        }

        @Override
        public void tick() {
            super.tick();
        }

        @Override
        public boolean shouldContinueExecuting() {
            return this.penguin.isInWater() && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.penguin.world, this.destinationBlock);
        }

        @Override
        public boolean shouldExecute() {
            return this.penguin.isInWater() && super.shouldExecute();
        }

        @Override
        protected boolean shouldMoveTo(IWorldReader reader, @Nonnull BlockPos pos) {
            Block block = reader.getBlockState(pos).getBlock();
            return !(block instanceof FlowingFluidBlock);
        }
    }
}
