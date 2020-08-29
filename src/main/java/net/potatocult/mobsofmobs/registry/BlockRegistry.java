package net.potatocult.mobsofmobs.registry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.potatocult.mobsofmobs.blocks.CarvedMelonBlock;
import net.potatocult.mobsofmobs.core.MobsOfMobs;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
            MobsOfMobs.MODID);

    public static final RegistryObject<Block> CARVED_MELON = BLOCKS.register("carved_melon",
            () -> new CarvedMelonBlock(Block.Properties.create(Material.GOURD, MaterialColor.LIME).hardnessAndResistance(1.0F)
                    .harvestTool(ToolType.AXE).sound(SoundType.WOOD)));


    public static final RegistryObject<Block> JACK_O_MELON = BLOCKS.register("jack_o_melon",
            () -> new CarvedMelonBlock(Block.Properties.create(Material.GOURD, MaterialColor.LIME).hardnessAndResistance(1.0F)
                    .harvestTool(ToolType.AXE).sound(SoundType.WOOD).lightValue(15)));

}