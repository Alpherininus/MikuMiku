package org.alpherininus.mikumiku.common.entitys.animated.model;

import net.minecraft.resources.ResourceLocation;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.common.entitys.animated.DeathKnightEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DeathKnightModel extends AnimatedGeoModel<DeathKnightEntity> {
    @Override
    public ResourceLocation getModelResource(DeathKnightEntity dk) {
        return new ResourceLocation(MikuMiku.MODID, "geo/dk.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DeathKnightEntity dk) {
        return new ResourceLocation(MikuMiku.MODID, "textures/entity/boss/dk.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DeathKnightEntity animatable) {
        return new ResourceLocation(MikuMiku.MODID, "animations/dk.animation.json");
    }
}
