package org.alpherininus.mikumiku.core.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.alpherininus.mikumiku.MikuMiku;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MikuMiku.MODID);

    public static final RegistryObject<Block> MIKU_BLOCK = BLOCKS.register("miku_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));



}
