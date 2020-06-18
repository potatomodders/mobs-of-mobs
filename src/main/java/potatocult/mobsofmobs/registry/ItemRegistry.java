package potatocult.mobsofmobs.registry;

import com.google.common.base.Preconditions;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.BeehiveTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import potatocult.mobsofmobs.core.MobsOfMobs;
import potatocult.mobsofmobs.items.ItemHolder;
import potatocult.mobsofmobs.items.ItemTier;
import potatocult.mobsofmobs.items.eggs.GoldGolemSpawnEgg;
import potatocult.mobsofmobs.items.eggs.PenguinSpawnEgg;
import potatocult.mobsofmobs.items.eggs.PyromancerSpawnEgg;
import potatocult.mobsofmobs.items.eggs.WightSpawnEgg;

import javax.annotation.Nonnull;

import java.util.List;
import java.util.Random;

import static potatocult.mobsofmobs.core.MobsOfMobs.LOGGER;

@ObjectHolder(MobsOfMobs.MODID)
public class ItemRegistry {
    @Mod.EventBusSubscriber(modid = MobsOfMobs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Register {

        @SubscribeEvent
        public static void RegisterItems(final RegistryEvent.Register<Item> event) {
            final Item[] items = {
                    new Item(new Item.Properties().group(MobsOfMobs.GROUP).maxStackSize(64).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_ingot")),
                    new SwordItem(ItemTier.MITHRIL, (int) 2.5, (float) -2.4, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_sword")),
                    new PickaxeItem(ItemTier.MITHRIL, (int) 0.85, (float) -2.8, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_pickaxe")),
                    new ShovelItem(ItemTier.MITHRIL, (float) 0.70, (float) -3.0, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_shovel")),
                    new AxeItem(ItemTier.MITHRIL, (float) 3.25, (float) -3.1, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_axe")),
                    // Wait for 1.16 // new HoeItem(ItemTier.MITHRIL, (float) -1.5, new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_hoe")),
                    ItemHolder.MITHRIL_SHEARS = new ShearsItem(new Item.Properties().group(MobsOfMobs.GROUP).setNoRepair().maxDamage(763).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("mithril_shears")),
                    new Item(new Item.Properties().group(MobsOfMobs.GROUP).rarity(MobsOfMobs.getRarity())).setRegistryName(MobsOfMobs.location("penguin_feather")),

                    new GoldGolemSpawnEgg(16500000, 11998228).setRegistryName(MobsOfMobs.location("gold_golem_spawn_egg")),
                    new PenguinSpawnEgg(16500000, 11998228).setRegistryName(MobsOfMobs.location("penguin_spawn_egg")),
                    new PyromancerSpawnEgg(11998228, 16500000).setRegistryName(MobsOfMobs.location("pyromancer_spawn_egg")),
                    new WightSpawnEgg(16500000, 11998228).setRegistryName(MobsOfMobs.location("wight_spawn_egg"))
            };

            DispenserBlock.registerDispenseBehavior(ItemHolder.MITHRIL_SHEARS.asItem(), new OptionalDispenseBehavior() {

                @SuppressWarnings("deprecation")
                public @Nonnull
                ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                    World world = source.getWorld();
                    if (!world.isRemote()) {
                        this.successful = false;
                        BlockPos blockpos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));

                        for (Entity entity : world.getEntitiesInAABBexcluding(null, new AxisAlignedBB(blockpos), e -> !e.isSpectator() && e instanceof IShearable)) {
                            IShearable target = (IShearable) entity;
                            if (target.isShearable(stack, world, blockpos)) {
                                List<ItemStack> drops = target.onSheared(stack, entity.world, blockpos,
                                        EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack));
                                Random rand = new Random();
                                drops.forEach(d -> {
                                    ItemEntity ent = entity.entityDropItem(d, 1.0F);
                                    if (ent == null) return;
                                    ent.setMotion(ent.getMotion().add(((rand.nextFloat() - rand.nextFloat()) * 0.1F), (rand.nextFloat() * 0.05F), ((rand.nextFloat() - rand.nextFloat()) * 0.1F)));
                                });
                                if (stack.attemptDamageItem(1, world.rand, null)) {
                                    stack.setCount(0);
                                }

                                this.successful = true;
                                break;
                            }
                            if (!this.successful) {
                                BlockState blockstate = world.getBlockState(blockpos);
                                if (blockstate.isIn(BlockTags.BEEHIVES)) {
                                    int i = blockstate.get(BeehiveBlock.HONEY_LEVEL);
                                    if (i >= 5) {
                                        if (stack.attemptDamageItem(1, world.rand, null)) {
                                            stack.setCount(0);
                                        }

                                        BeehiveBlock.dropHoneyComb(world, blockpos);
                                        ((BeehiveBlock) blockstate.getBlock()).takeHoney(world, blockstate, blockpos, null, BeehiveTileEntity.State.BEE_RELEASED);
                                        this.successful = true;
                                    }
                                }
                            }
                        }
                    }
                    return stack;
                }
            });

            for (final Item item: items) {
                Preconditions.checkNotNull(item.getRegistryName(), "Block: %s has a NULL registry name", item);
                event.getRegistry().register(item);
            }
            LOGGER.debug("Registered Entities");
        }
    }
}
