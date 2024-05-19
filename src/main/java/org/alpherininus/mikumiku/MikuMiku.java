package org.alpherininus.mikumiku;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.alpherininus.mikumiku.common.entitys.animated.TPosingEntity;
import org.alpherininus.mikumiku.common.entitys.animated.renderer.DeathKnightRenderer;
import org.alpherininus.mikumiku.common.entitys.animated.renderer.StairsRenderer;
import org.alpherininus.mikumiku.common.entitys.animated.renderer.TPosingRenderer;
import org.alpherininus.mikumiku.common.entitys.animated.renderer.WulfRenderer;
import org.alpherininus.mikumiku.core.init.*;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

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
        EntityTypesInit.register(modEventBus);

        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()-> {
            VillagerInit.registerPOIs();
        });

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(EntityTypesInit.DK.get(), DeathKnightRenderer::new);
            EntityRenderers.register(EntityTypesInit.TP.get(), TPosingRenderer::new);
            EntityRenderers.register(EntityTypesInit.STAIRS.get(), StairsRenderer::new);
            EntityRenderers.register(EntityTypesInit.WU.get(), WulfRenderer::new);

        }
    }
}
