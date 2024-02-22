package org.alpherininus.mikumiku.common.items;

import com.mojang.brigadier.StringReader;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.alpherininus.mikumiku.client.networking.Messages;
import org.alpherininus.mikumiku.client.networking.packet.ExampleC2SPacket;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MikuSwordItem extends SwordItem {

    public MikuSwordItem(Tier tier, int attack, float speed, Properties properties) {
        super(tier, attack, speed, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

            outputRandomNumber(player);
            player.getCooldowns().addCooldown(this, 20);

            if (getRandomNumber() == 3) {
                Messages.sendToServer(new ExampleC2SPacket());
            }
            if (getRandomNumber() == 1) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 1));
            }
            if (getRandomNumber() == 5) {
                player.addItem(new ItemStack(Items.DIAMOND));
            }
            if (getRandomNumber() == 7) {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 150, 1));
            }
            if (getRandomNumber() == 9) {
                player.hurt(new DamageSource("mikumiku.Magic"), 0.5f);
            }
            if (getRandomNumber() == 2) {
                player.level.disconnect();
            }
            if (getRandomNumber() == 4) {

            }
            if (getRandomNumber() == 6) {

            }
            if (getRandomNumber() == 8) {
                level.playSound(player, new BlockPos(0, 0, 0), SoundEvents.AMBIENT_CAVE, SoundSource.AMBIENT, 1.0f, 1.0f);
            }
            if (getRandomNumber() == 0) {
                player.sendSystemMessage(Component.literal(" "));
            }
        }
        return super.use(level, player, hand);
    }

    private void outputRandomNumber(Player player) {
        player.sendSystemMessage(Component.literal("Your Number is " + getRandomNumber()));
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
