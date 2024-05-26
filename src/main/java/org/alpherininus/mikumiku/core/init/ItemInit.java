package org.alpherininus.mikumiku.core.init;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.common.items.MikuSwordItem;
import org.alpherininus.mikumiku.common.items.WulfSpawnEggItem;
import org.alpherininus.mikumiku.core.utils.materials.itemtier.ItemAttackDamage;
import org.alpherininus.mikumiku.core.utils.materials.itemtier.ItemAttackSpeed;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MikuMiku.MODID);

    public static final RegistryObject<Item> MIKU_SWORD = ITEMS.register("miku_sword",
            () -> new MikuSwordItem(Tiers.WOOD, ItemAttackDamage.Sword.getNetherite(), ItemAttackSpeed.getSword(),
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<Item> DK_SPAWN_EGG = ITEMS.register("dk_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypesInit.DK, 0x22b341, 0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<Item> TP_SPAWN_EGG = ITEMS.register("tp_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypesInit.TP, 0x22b341, 0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<Item> ST_SPAWN_EGG = ITEMS.register("st_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypesInit.STAIRS, 0x22b341, 0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<Item> WU_SPAWN_EGG = ITEMS.register("wulf_spawn_egg",
            () -> new WulfSpawnEggItem(EntityTypesInit.WU, 0x22b341, 0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT), ItemAttackDamage.Sword.getNetherite(), ItemAttackSpeed.setAttackSpeed(2.0f), Tiers.DIAMOND));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
