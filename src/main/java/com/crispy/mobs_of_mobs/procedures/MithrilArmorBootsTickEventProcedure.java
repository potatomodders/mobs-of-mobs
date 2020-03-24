package com.crispy.mobs_of_mobs.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.block.Blocks;

import java.util.Random;

import com.crispy.mobs_of_mobs.MobsofMobsElements;

@MobsofMobsElements.ModElement.Tag
public class MithrilArmorBootsTickEventProcedure extends MobsofMobsElements.ModElement {
	public MithrilArmorBootsTickEventProcedure(MobsofMobsElements instance) {
		super(instance, 29);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MithrilArmorBootsTickEvent!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MithrilArmorBootsTickEvent!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MithrilArmorBootsTickEvent!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure MithrilArmorBootsTickEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MithrilArmorBootsTickEvent!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		World world = (World) dependencies.get("world");
		double dmgitemthis = 0;
		dmgitemthis = (double) 1;
		if ((((itemstack).getOrCreateTag().getBoolean("motretimes")) == (true))) {
			itemstack.addAttributeModifier("generic.maxHealth", new AttributeModifier("generic.maxHealth", 2, AttributeModifier.Operation.byId(0)),
					EquipmentSlotType.FEET);
			{
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().sendMessage(new StringTextComponent("works"));
			}
		}
		(itemstack).getOrCreateTag().putBoolean("moretimes", (true));
		if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock()))) {
			world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.STONE.getDefaultState(), 3);
			dmgitemthis = (double) ((dmgitemthis) + 1);
		}
		if ((((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))).getBlock() == Blocks.LAVA.getDefaultState()
						.getBlock()))) {
			world.setBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z), Blocks.STONE.getDefaultState(), 3);
			dmgitemthis = (double) ((dmgitemthis) + 1);
		}
		if ((((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == Blocks.LAVA.getDefaultState()
						.getBlock()))) {
			world.setBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z), Blocks.STONE.getDefaultState(), 3);
			dmgitemthis = (double) ((dmgitemthis) + 1);
		}
		if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.LAVA.getDefaultState()
						.getBlock()))) {
			world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)), Blocks.STONE.getDefaultState(), 3);
			dmgitemthis = (double) ((dmgitemthis) + 1);
		}
		if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)))).getBlock() == Blocks.LAVA.getDefaultState()
						.getBlock()))) {
			world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)), Blocks.STONE.getDefaultState(), 3);
			dmgitemthis = (double) ((dmgitemthis) + 1);
		}
		if ((((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z - 1)))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z - 1)))).getBlock() == Blocks.LAVA.getDefaultState()
						.getBlock()))) {
			world.setBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z - 1)), Blocks.STONE.getDefaultState(), 3);
			dmgitemthis = (double) ((dmgitemthis) + 1);
		}
		if ((((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.LAVA.getDefaultState()
						.getBlock()))) {
			world.setBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 1)), Blocks.STONE.getDefaultState(), 3);
			dmgitemthis = (double) ((dmgitemthis) + 1);
		}
		if ((((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.LAVA.getDefaultState()
						.getBlock()))) {
			world.setBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 1)), Blocks.STONE.getDefaultState(), 3);
			dmgitemthis = (double) ((dmgitemthis) + 1);
		}
		if ((((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 1)))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 1)))).getBlock() == Blocks.LAVA.getDefaultState()
						.getBlock()))) {
			world.setBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 1)), Blocks.STONE.getDefaultState(), 3);
			dmgitemthis = (double) ((dmgitemthis) + 1);
		}
		if ((((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.LAVA.getDefaultState()
						.getBlock()))) {
			world.setBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z + 1)), Blocks.STONE.getDefaultState(), 3);
			dmgitemthis = (double) ((dmgitemthis) + 1);
		}
		if ((false)) {
			if (itemstack.attemptDamageItem((int) 1, new Random(), null)) {
				itemstack.shrink(1);
				itemstack.setDamage(0);
			}
		}
	}
}
