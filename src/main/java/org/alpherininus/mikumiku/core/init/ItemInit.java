package org.alpherininus.mikumiku.core.init;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.core.utils.materials.itemtier.ItemAttackDamage;
import org.alpherininus.mikumiku.core.utils.materials.itemtier.ItemAttackSpeed;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MikuMiku.MODID);

    public static final RegistryObject<Item> MIKU_BLOCK_ITEM = ITEMS.register("miku_block",
            () -> new BlockItem(BlockInit.MIKU_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> MIKU_SWORD = ITEMS.register("miku_sword",
            () -> new SwordItem(Tiers.WOOD, ItemAttackDamage.Sword.getNetherite(), ItemAttackSpeed.getSword(),
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
