
package com.crispy.mobs_of_mobs.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import com.crispy.mobs_of_mobs.procedures.MithrilArmorBootsTickEventProcedure;
import com.crispy.mobs_of_mobs.itemgroup.MobsOfMobsStuffItemGroup;
import com.crispy.mobs_of_mobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class BootsOfFireItem extends MobsofMobsElements.ModElement {
	@ObjectHolder("mobs_of_mobs:bootsoffirehelmet")
	public static final Item helmet = null;
	@ObjectHolder("mobs_of_mobs:bootsoffirebody")
	public static final Item body = null;
	@ObjectHolder("mobs_of_mobs:bootsoffirelegs")
	public static final Item legs = null;
	@ObjectHolder("mobs_of_mobs:bootsoffireboots")
	public static final Item boots = null;
	public BootsOfFireItem(MobsofMobsElements instance) {
		super(instance, 29);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 50;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{1, 5, 6, 2}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 16;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot"));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(FlameEssenceItem.block, (int) (1)));
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "bootsoffire";
			}

			public float getToughness() {
				return 2.5f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.FEET, new Item.Properties().group(MobsOfMobsStuffItemGroup.tab)) {
			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "mobs_of_mobs:textures/models/armor/lav__layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}

			@Override
			public void onArmorTick(ItemStack itemstack, World world, PlayerEntity entity) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("itemstack", itemstack);
					$_dependencies.put("world", world);
					MithrilArmorBootsTickEventProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("bootsoffireboots"));
	}
}
