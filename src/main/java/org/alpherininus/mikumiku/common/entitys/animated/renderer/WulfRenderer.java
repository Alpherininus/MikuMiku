package org.alpherininus.mikumiku.common.entitys.animated.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.common.entitys.animated.StairsEntity;
import org.alpherininus.mikumiku.common.entitys.animated.WulfEntity;
import org.alpherininus.mikumiku.common.entitys.animated.model.StairsModel;
import org.alpherininus.mikumiku.common.entitys.animated.model.WulfModel;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WulfRenderer extends GeoEntityRenderer<WulfEntity> {
    public WulfRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WulfModel());
        this.shadowRadius = 1.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(WulfEntity animatable) {
        return new ResourceLocation(MikuMiku.MODID, "textures/entity/boss/wulf.png");
    }

    @Override
    public RenderType getRenderType(WulfEntity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource,
                                    @Nullable VertexConsumer buffer, int packedLight,
                                    ResourceLocation texture) {
        poseStack.scale(1.5f, 1.5f, 1.5f);

        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
