package org.alpherininus.mikumiku.common.entitys.animated.model;

import net.minecraft.resources.ResourceLocation;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.common.entitys.animated.DeathKnightEntity;
import org.alpherininus.mikumiku.common.entitys.animated.TPosingEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TPosingModel extends AnimatedGeoModel<TPosingEntity> {
    @Override
    public ResourceLocation getModelResource(TPosingEntity tp) {
        return new ResourceLocation(MikuMiku.MODID, "geo/tp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TPosingEntity tp) {
        return new ResourceLocation(MikuMiku.MODID, "textures/entity/boss/tp.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TPosingEntity animatable) {
        return new ResourceLocation(MikuMiku.MODID, "animations/tp.animation.json");
    }
}
