package org.alpherininus.mikumiku.core.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.common.entitys.animated.DeathKnightEntity;
import org.alpherininus.mikumiku.common.entitys.animated.StairsEntity;
import org.alpherininus.mikumiku.common.entitys.animated.TPosingEntity;
import org.alpherininus.mikumiku.common.entitys.animated.WulfEntity;

public class EntityTypesInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MikuMiku.MODID);

    public static final RegistryObject<EntityType<DeathKnightEntity>> DK =
            ENTITY_TYPES.register("death_knight", () -> EntityType.Builder.of(DeathKnightEntity::new, MobCategory.MONSTER)
                    .sized(1f, 2.5f)
                    .build(new ResourceLocation(MikuMiku.MODID, "death_knight").toString()));

    public static final RegistryObject<EntityType<TPosingEntity>> TP =
            ENTITY_TYPES.register("tposing", () -> EntityType.Builder.of(TPosingEntity::new, MobCategory.MONSTER)
                    .sized(1f, 2.0f)
                    .build(new ResourceLocation(MikuMiku.MODID, "tposing").toString()));

    public static final RegistryObject<EntityType<StairsEntity>> STAIRS =
            ENTITY_TYPES.register("stairs", () -> EntityType.Builder.of(StairsEntity::new, MobCategory.MONSTER)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(MikuMiku.MODID, "stairs").toString()));

    public static final RegistryObject<EntityType<WulfEntity>> WU =
            ENTITY_TYPES.register("wulf", () -> EntityType.Builder.of(WulfEntity::new, MobCategory.MONSTER)
                    .sized(1.9f, 1.9f)
                    .build(new ResourceLocation(MikuMiku.MODID, "wulf").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
