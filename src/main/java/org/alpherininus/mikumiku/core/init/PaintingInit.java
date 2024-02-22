package org.alpherininus.mikumiku.core.init;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.alpherininus.mikumiku.MikuMiku;

public class PaintingInit {
    private static final int x16 = 16;
    private static final int x32 = 32;
    private static final int x64 = 64;

    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MikuMiku.MODID);

    public static final RegistryObject<PaintingVariant> PAINTING_VARIANT_A = PAINTING_VARIANTS.register("paint_a", ()-> new PaintingVariant(x16, x16));
    public static final RegistryObject<PaintingVariant> PAINTING_VARIANT_B = PAINTING_VARIANTS.register("paint_b", ()-> new PaintingVariant(x16, x32));
    public static final RegistryObject<PaintingVariant> PAINTING_VARIANT_C = PAINTING_VARIANTS.register("paint_c", ()-> new PaintingVariant(x32, x16));
    public static final RegistryObject<PaintingVariant> PAINTING_VARIANT_D = PAINTING_VARIANTS.register("paint_d", ()-> new PaintingVariant(x32, x32));
    public static final RegistryObject<PaintingVariant> PAINTING_VARIANT_E = PAINTING_VARIANTS.register("paint_e", ()-> new PaintingVariant(x32, x64));
    public static final RegistryObject<PaintingVariant> PAINTING_VARIANT_F = PAINTING_VARIANTS.register("paint_f", ()-> new PaintingVariant(x64, x32));
    public static final RegistryObject<PaintingVariant> PAINTING_VARIANT_G = PAINTING_VARIANTS.register("paint_g", ()-> new PaintingVariant(x64, x64));
    public static final RegistryObject<PaintingVariant> PAINTING_VARIANT_H = PAINTING_VARIANTS.register("paint_h", ()-> new PaintingVariant(x64, 48));
    public static final RegistryObject<PaintingVariant> PAINTING_VARIANT_I = PAINTING_VARIANTS.register("paint_i", ()-> new PaintingVariant(48, x64));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
