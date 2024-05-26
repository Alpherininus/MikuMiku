package org.alpherininus.mikumiku.core.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.alpherininus.mikumiku.MikuMiku;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUND_REG = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MikuMiku.MODID);

    public static final RegistryObject<SoundEvent> GREAT_CRAFT_WOLF = registerSoundEvent("great_craft_wolf_sif");
    public static final RegistryObject<SoundEvent> STAIRS = registerSoundEvent("stairs");


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation location = new ResourceLocation(MikuMiku.MODID, name);
        return SOUND_REG.register(name, ()-> new SoundEvent(location));
    }

    public static void register(IEventBus eventBus) {
        SOUND_REG.register(eventBus);
    }
}
