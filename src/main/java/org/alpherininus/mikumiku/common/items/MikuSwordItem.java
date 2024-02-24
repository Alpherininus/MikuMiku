package org.alpherininus.mikumiku.common.items;

import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.alpherininus.mikumiku.MikuMiku;
import org.alpherininus.mikumiku.core.init.EntityTypesInit;
import org.alpherininus.mikumiku.core.init.ItemInit;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MikuSwordItem extends SwordItem {
    private static final Logger LOGGER = LogUtils.getLogger();

    public MikuSwordItem(Tier tier, int attack, float speed, Properties properties) {
        super(tier, attack, speed, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ServerPlayer serverPlayer = null;
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

            outputRandomNumber(player);
            player.getCooldowns().addCooldown(this, 20);

            if (getRandomNumber() == 3) {

            }
            if (getRandomNumber() == 1) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 1));
            }
            if (getRandomNumber() == 5) {
                player.addItem(new ItemStack(Items.ENDER_PEARL));
            }
            if (getRandomNumber() == 7) {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 150, 1));
            }
            if (getRandomNumber() == 9) {
                player.hurt(new DamageSource(MikuMiku.MODID + ".Magic"), 0.5f);
            }
            if (getRandomNumber() == 2) {
                try {
                    player.level.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (getRandomNumber() == 4) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            }
            if (getRandomNumber() == 6) {

            }
            if (getRandomNumber() == 8) {
                level.playSound(null, player.getOnPos(), SoundEvents.AMBIENT_CAVE, SoundSource.AMBIENT, 0.5F, level.random.nextFloat() * 0.1f + 0.9F);

            }
            if (getRandomNumber() == 0) {
                player.sendSystemMessage(Component.literal(" "));
            }
        }
        return super.use(level, player, hand);
    }

    private void outputRandomNumber(Player player) {
        LOGGER.info("MikuSwordItem || " + Minecraft.getInstance().getUser().getName() + " Number is " + getRandomNumber());

    }

    private int getRandomNumber() {
        return RandomSource.createNewThreadLocalInstance().nextInt(10);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            list.add(Component.literal("Right Click to get a random number!").withStyle(ChatFormatting.AQUA));
        } else {
            list.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.YELLOW));
        }

        super.appendHoverText(stack, level, list, tooltipFlag);
    }

}
