package net.potatocult.mobsofmobs.entities.monster;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.potatocult.mobsofmobs.entities.ai.goal.WightAttackGoal;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Random;

public class WightEntity extends MonsterEntity {
    private static final DataParameter<Integer> VILLAGER_TYPE = EntityDataManager.createKey(ZombieEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> DROWNING = EntityDataManager.createKey(ZombieEntity.class, DataSerializers.BOOLEAN);
    private int inWaterTime;
    private int drownedConversionTime;

    public WightEntity(EntityType<? extends WightEntity> type, World worldIn) {
        super(type, worldIn);
        this.setHealth(this.getMaxHealth());
        this.experienceValue = 5;
    }

    public static boolean spawnPredicate(EntityType<? extends MonsterEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK;
    }

    public static boolean spawning(EntityType<WightEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.4));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI() {
        this.goalSelector.addGoal(2, new WightAttackGoal(this, 1.25D, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(23.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.21F);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.5D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    }

    protected void registerData() {
        super.registerData();
        this.getDataManager().register(VILLAGER_TYPE, 0);
        this.getDataManager().register(DROWNING, false);
    }

    public boolean isDrowning() {
        return this.getDataManager().get(DROWNING);
    }

    protected boolean shouldDrown() {
        return true;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }


    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        float f = difficultyIn.getClampedAdditionalDifficulty();
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);

        if (spawnDataIn instanceof ZombieEntity.GroupData) {
            ZombieEntity.GroupData zombieentity$groupdata = (ZombieEntity.GroupData) spawnDataIn;

            this.setEquipmentBasedOnDifficulty(difficultyIn);
            this.setEnchantmentBasedOnDifficulty(difficultyIn);
        }

        if (this.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty()) {
            LocalDate localdate = LocalDate.now();
            int i = localdate.get(ChronoField.DAY_OF_MONTH);
            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && this.rand.nextFloat() < 0.25F) {
                this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                this.inventoryArmorDropChances[EquipmentSlotType.HEAD.getIndex()] = 0.0F;
            }
        }

        return spawnDataIn;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        if (this.rand.nextInt(200) == 0) {
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD, 1));
            this.inventoryHandsDropChances[EquipmentSlotType.MAINHAND.getSlotIndex()] = 1.0f;
        } else if (this.rand.nextInt(100) == 0) {
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SHOVEL, 1));
            this.inventoryHandsDropChances[EquipmentSlotType.MAINHAND.getSlotIndex()] = 1.0f;
        } else if (this.rand.nextInt(50) == 0) {
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.WOODEN_SHOVEL, 1));
            this.inventoryHandsDropChances[EquipmentSlotType.MAINHAND.getSlotIndex()] = 1.0f;
        } else if (this.rand.nextInt(30) == 0) {
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.WOODEN_HOE, 1));
            this.inventoryHandsDropChances[EquipmentSlotType.MAINHAND.getSlotIndex()] = 1.0f;
        }
        if (this.rand.nextInt(500) == 0) {
            this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.OAK_DOOR, 1));
            this.inventoryHandsDropChances[EquipmentSlotType.OFFHAND.getSlotIndex()] = 1.0f;
        }
    }

    public void tick() {
        if (!this.world.isRemote && this.isAlive()) {
            if (this.isDrowning()) {
                --this.drownedConversionTime;
                if (this.drownedConversionTime < 0) {
                    this.onDrowned();
                }
            } else if (this.shouldDrown()) {
                if (this.areEyesInFluid(FluidTags.WATER)) {
                    ++this.inWaterTime;
                    if (this.inWaterTime >= 600) {
                        this.startDrowning(300);
                    }
                } else {
                    this.inWaterTime = -1;
                }
            }
        }

        super.tick();
    }

    public void livingTick() {
        if (this.isAlive()) {
            boolean flag = this.shouldBurnInDay() && this.isInDaylight();
            if (flag) {
                ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageable()) {
                        itemstack.setDamage(itemstack.getDamage() + this.rand.nextInt(2));
                        if (itemstack.getDamage() >= itemstack.getMaxDamage()) {
                            this.sendBreakAnimation(EquipmentSlotType.HEAD);
                            this.setItemStackToSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    this.setFire(8);
                }
            }
        }

        super.livingTick();
    }

    private void startDrowning(int p_204704_1_) {
        this.drownedConversionTime = p_204704_1_;
        this.getDataManager().set(DROWNING, true);
    }

    protected void onDrowned() {
        this.func_213698_b(EntityType.DROWNED);
        this.world.playEvent(null, 1040, new BlockPos(this), 0);
    }

    protected void func_213698_b(EntityType<? extends DrownedEntity> p_213698_1_) {
        if (!this.removed) {
            DrownedEntity wightentity = p_213698_1_.create(this.world);
            wightentity.copyLocationAndAnglesFrom(this);
            wightentity.setCanPickUpLoot(this.canPickUpLoot());
            wightentity.setNoAI(this.isAIDisabled());

            for (EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
                ItemStack itemstack = this.getItemStackFromSlot(equipmentslottype);
                if (!itemstack.isEmpty()) {
                    wightentity.setItemStackToSlot(equipmentslottype, itemstack.copy());
                    wightentity.setDropChance(equipmentslottype, this.getDropChance(equipmentslottype));
                    itemstack.setCount(0);
                }
            }

            if (this.hasCustomName()) {
                wightentity.setCustomName(this.getCustomName());
                wightentity.setCustomNameVisible(this.isCustomNameVisible());
            }

            if (this.isNoDespawnRequired()) {
                wightentity.enablePersistence();
            }

            wightentity.setInvulnerable(this.isInvulnerable());
            this.world.addEntity(wightentity);
            this.remove();
        }
    }

    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (item instanceof SpawnEggItem && ((SpawnEggItem) item).hasType(itemstack.getTag(), this.getType())) {
            if (!this.world.isRemote) {
                WightEntity wightentity = (WightEntity) this.getType().create(this.world);
                if (wightentity != null) {
                    wightentity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), 0.0F, 0.0F);
                    this.world.addEntity(wightentity);
                    if (itemstack.hasDisplayName()) {
                        wightentity.setCustomName(itemstack.getDisplayName());
                    }

                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                }
            }

            return true;
        } else {
            return super.processInteract(player, hand);
        }
    }

    protected boolean shouldBurnInDay() {
        return true;
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("InWaterTime", this.isInWater() ? this.inWaterTime : -1);
        compound.putInt("DrownedConversionTime", this.isDrowning() ? this.drownedConversionTime : -1);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.inWaterTime = compound.getInt("InWaterTime");
        if (compound.contains("DrownedConversionTime", 33) && compound.getInt("DrownedConversionTime") > -1) {
            this.startDrowning(compound.getInt("DrownedConversionTime"));
        }

    }

    public void onKillEntity(LivingEntity entityLivingIn) {
        super.onKillEntity(entityLivingIn);
        if ((this.world.getDifficulty() == Difficulty.NORMAL || this.world.getDifficulty() == Difficulty.HARD) && entityLivingIn instanceof VillagerEntity) {
            if (this.world.getDifficulty() != Difficulty.HARD && this.rand.nextBoolean()) {
                return;
            }

            VillagerEntity villagerentity = (VillagerEntity) entityLivingIn;
            ZombieVillagerEntity zombievillagerentity = EntityType.ZOMBIE_VILLAGER.create(this.world);
            zombievillagerentity.copyLocationAndAnglesFrom(villagerentity);
            villagerentity.remove();
            zombievillagerentity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(zombievillagerentity)), SpawnReason.CONVERSION, null, null);
            zombievillagerentity.setVillagerData(villagerentity.getVillagerData());
            zombievillagerentity.setGossips(villagerentity.getGossip().serialize(NBTDynamicOps.INSTANCE).getValue());
            zombievillagerentity.setOffers(villagerentity.getOffers().write());
            zombievillagerentity.setEXP(villagerentity.getXp());
            zombievillagerentity.setNoAI(villagerentity.isAIDisabled());
            if (villagerentity.hasCustomName()) {
                zombievillagerentity.setCustomName(villagerentity.getCustomName());
                zombievillagerentity.setCustomNameVisible(villagerentity.isCustomNameVisible());
            }

            if (this.isNoDespawnRequired()) {
                zombievillagerentity.enablePersistence();
            }

            zombievillagerentity.setInvulnerable(this.isInvulnerable());
            this.world.addEntity(zombievillagerentity);
            this.world.playEvent(null, 1026, new BlockPos(this), 0);
        }

    }

    public void onStruckByLightning(LightningBoltEntity lightningBolt) {
        ZombiePigmanEntity zombiepigmanentity = EntityType.ZOMBIE_PIGMAN.create(this.world);
        zombiepigmanentity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
        zombiepigmanentity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
        zombiepigmanentity.setNoAI(this.isAIDisabled());
        if (this.hasCustomName()) {
            zombiepigmanentity.setCustomName(this.getCustomName());
            zombiepigmanentity.setCustomNameVisible(this.isCustomNameVisible());
        }

        this.world.addEntity(zombiepigmanentity);
        this.remove();
    }
}
