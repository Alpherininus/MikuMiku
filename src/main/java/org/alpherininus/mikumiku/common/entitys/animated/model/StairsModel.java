package org.alpherininus.mikumiku.common.entitys.animated.model;

import net.minecraft.resources.ResourceLocation;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.common.entitys.animated.StairsEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StairsModel extends AnimatedGeoModel<StairsEntity> {
    @Override
    public ResourceLocation getModelResource(StairsEntity tp) {
        return new ResourceLocation(MikuMiku.MODID, "geo/stairs.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StairsEntity tp) {
        return new ResourceLocation(MikuMiku.MODID, "textures/entity/boss/stairs.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StairsEntity animatable) {
        return new ResourceLocation(MikuMiku.MODID, "animations/stairs.animation.json");
    }
}
