package org.alpherininus.mikumiku.common.entitys.animated.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.common.entitys.animated.DeathKnightEntity;
import org.alpherininus.mikumiku.common.entitys.animated.model.DeathKnightModel;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DeathKnightRenderer extends GeoEntityRenderer<DeathKnightEntity> {
    public DeathKnightRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DeathKnightModel());
        this.shadowRadius = 1.1f;
    }

    @Override
    public ResourceLocation getTextureLocation(DeathKnightEntity animatable) {
        return new ResourceLocation(MikuMiku.MODID, "textures/entity/boss/dk.png");

    }

    @Override
    public RenderType getRenderType(DeathKnightEntity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource,
                                    @Nullable VertexConsumer buffer, int packedLight,
                                    ResourceLocation texture) {
        poseStack.scale(1.0f, 1.0f, 1.0f);

        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
