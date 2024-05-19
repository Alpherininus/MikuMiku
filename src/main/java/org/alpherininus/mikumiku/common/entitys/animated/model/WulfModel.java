package org.alpherininus.mikumiku.common.entitys.animated.model;

import net.minecraft.resources.ResourceLocation;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.common.entitys.animated.StairsEntity;
import org.alpherininus.mikumiku.common.entitys.animated.WulfEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WulfModel extends AnimatedGeoModel<WulfEntity> {
    @Override
    public ResourceLocation getModelResource(WulfEntity tp) {
        return new ResourceLocation(MikuMiku.MODID, "geo/wu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WulfEntity tp) {
        return new ResourceLocation(MikuMiku.MODID, "textures/entity/boss/wulf.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WulfEntity animatable) {
        return new ResourceLocation(MikuMiku.MODID, "animations/wu.animation.json");
    }
}
