package org.alpherininus.mikumiku;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.alpherininus.mikumiku.client.networking.Messages;
import org.alpherininus.mikumiku.core.init.BlockInit;
import org.alpherininus.mikumiku.core.init.ItemInit;
import org.alpherininus.mikumiku.core.init.PaintingInit;
import org.alpherininus.mikumiku.core.init.VillagerInit;
import org.slf4j.Logger;

@Mod(MikuMiku.MODID)
public class MikuMiku {
    // TODO FUN PROJECT - MOD
    public static final String MODID = "mikumiku";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MikuMiku() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.register(modEventBus);
        BlockInit.register(modEventBus);
        VillagerInit.register(modEventBus);
        PaintingInit.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()-> {
            VillagerInit.registerPOIs();
        });

        Messages.register();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
