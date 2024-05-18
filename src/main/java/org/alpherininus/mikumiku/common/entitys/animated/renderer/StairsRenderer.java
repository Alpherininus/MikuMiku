package org.alpherininus.mikumiku.common.entitys.animated.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.common.entitys.animated.StairsEntity;
import org.alpherininus.mikumiku.common.entitys.animated.model.StairsModel;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class StairsRenderer extends GeoEntityRenderer<StairsEntity> {
    public StairsRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StairsModel());
        this.shadowRadius = 0.95f;
    }

    @Override
    public ResourceLocation getTextureLocation(StairsEntity animatable) {
        return new ResourceLocation(MikuMiku.MODID, "textures/entity/boss/stairs.png");

    }

    @Override
    public RenderType getRenderType(StairsEntity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource,
                                    @Nullable VertexConsumer buffer, int packedLight,
                                    ResourceLocation texture) {
        poseStack.scale(0.5f, 0.5f, 0.5f);

        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
