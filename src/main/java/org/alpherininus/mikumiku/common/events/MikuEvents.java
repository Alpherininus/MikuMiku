package org.alpherininus.mikumiku.common.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.core.init.ItemInit;
import org.alpherininus.mikumiku.core.init.VillagerInit;

import java.util.List;

@Mod.EventBusSubscriber(modid = MikuMiku.MODID)
public class MikuEvents {
    private static int villagerStoneLevel = 1;
    private static int villagerIronLevel = 2;
    private static int villagerGoldLevel = 3;
    private static int villagerEmeraldLevel = 4;
    private static int villagerDiamondLevel = 5;

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerInit.MIKU_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            ItemStack tradeA = new ItemStack(ItemInit.MIKU_SWORD.get(), 1);

            trades.get(villagerStoneLevel).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 6),
                    tradeA, 10, 8, 0.02F));

            trades.get(villagerIronLevel).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 4),
                    tradeA, 10, 8, 0.02F));

            trades.get(villagerGoldLevel).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2),
                    tradeA, 8, 8, 0.02F));

            trades.get(villagerEmeraldLevel).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1),
                    tradeA, 6, 8, 0.02F));

            trades.get(villagerDiamondLevel).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.DIRT, 6),
                    tradeA, 2, 8, 0.02F));


        }
    }
}
