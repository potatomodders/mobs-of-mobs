package potatocult.mobsofmobs.entities;

import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.Set;

public class PenguinEntity extends TameableEntity {
    private static final Item DEADLY_ITEM = Items.COOKIE;
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);

    public PenguinEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        this.setHealth(this.getMaxHealth());
    }

    protected void registerGoals() {
        this.sitGoal = new SitGoal(this);
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, this.sitGoal);
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
    }

    protected void applyEntityAI() {
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(8.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.13F);
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

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }

    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() instanceof SpawnEggItem) {
            return super.processInteract(player, hand);
        } else if (!this.isTamed() && TAME_ITEMS.contains(itemstack.getItem())) {
            if (!player.abilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            if (!this.isSilent()) {
                this.world.playSound((PlayerEntity)null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_PARROT_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
            }

            if (!this.world.isRemote) {
                if (this.rand.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.setTamedBy(player);
                    this.world.setEntityState(this, (byte)7);
                } else {
                    this.world.setEntityState(this, (byte)6);
                }
            }

            return true;
        } else if (itemstack.getItem() == DEADLY_ITEM) {
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
}
