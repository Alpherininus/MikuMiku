package org.alpherininus.mikumiku.core.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.core.utils.itemgroup.ModItemGroup;

import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MikuMiku.MODID);

    public static final RegistryObject<Block> MIKU_BLOCK = registryBlock("miku_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).sound(SoundType.BAMBOO)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //TODO Registry -> registryBlock oder BLOCKS.register

    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlock(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registryBlock(String name, RegistryObject<T> block) {
        ItemInit.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.TAB_MIKU_MOD)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
