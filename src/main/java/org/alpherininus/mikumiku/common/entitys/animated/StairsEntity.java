package org.alpherininus.mikumiku.common.entitys.animated;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.alpherininus.mikumiku.MikuMiku;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class StairsEntity extends Monster implements IAnimatable {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final Player players = Minecraft.getInstance().player;

    private final AnimationFactory factory = new AnimationFactory(this);
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(false);

    public StairsEntity(EntityType<? extends Monster> k, Level level) {
        super(k, level);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 9999.5D)
                .add(Attributes.ATTACK_DAMAGE, 4.5D)
                .add(Attributes.ATTACK_SPEED, 0.75D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.76D, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 2.5D));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected boolean canRide(@NotNull Entity entity) {
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));

        data.addAnimationController(new AnimationController(this, "attackcontroller", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void getAttackSound() {
        this.playSound(SoundEvents.WOOD_PLACE, 1.5f, level.random.nextFloat() * 0.1f + 0.9F);
    }

    @Override
    protected float getSoundVolume() {
        return level.random.nextFloat() * 0.2f + 0.8F;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WOOD_BREAK;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return SoundEvents.WOOD_BREAK;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WOOD_FALL;
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState state) {
        this.playSound(SoundEvents.WOOD_STEP, 0.15f, level.random.nextFloat() * 0.1f + 0.9F);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void customServerAiStep() {
        if (this.isNight()) {
            if (this.tickCount % 20 == 0) {
                this.heal(10.0F);
            }
        }
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

        if (this.tickCount % 230 == 0) {
            getRandomNumber();

            Player player = Minecraft.getInstance().player;
            assert player != null;

            Level world = Minecraft.getInstance().level;
            assert world != null;

            if (getRandomNumber() == 3) {
                player.sendSystemMessage(Component.literal("<Stair, Godslayer of Oak> Now I'm (a little) motivated!"));
            }
            if (getRandomNumber() == 1) {
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 25, 5));
                player.sendSystemMessage(Component.literal("<Stair, Godslayer of Oak> Where's your motivation?"));
            }
            if (getRandomNumber() == 5) {
                player.sendSystemMessage(Component.literal("<Stair, Godslayer of Oak> What's wrong?"));
            }
            if (getRandomNumber() == 7) {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 25, 2));
                player.sendSystemMessage(Component.literal("<Stair, Godslayer of Oak> Scum"));
            }
            if (getRandomNumber() == 9) {
                player.sendSystemMessage(Component.literal("<Stair, Godslayer of Oak> Die!"));
            }

            if (getRandomNumber() == 2) {
                player.sendSystemMessage(Component.literal("<Stair, Godslayer of Oak> Don't get so cocky!"));
            }

            if (getRandomNumber() == 4) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 25, 3));
                player.sendSystemMessage(Component.literal("<Stair, Godslayer of Oak> Too easy."));
            }

            if (getRandomNumber() == 6) {
                player.sendSystemMessage(Component.literal("<Stair, Godslayer of Oak> How boring!"));
            }

            if (getRandomNumber() == 8) {
                player.sendSystemMessage(Component.literal("<Stair, Godslayer of Oak> You trash!"));
            }
            if (getRandomNumber() == 0) {
                player.sendSystemMessage(Component.literal("<Stair, Godslayer of Oak> This is the end!"));
                player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 45, 5));
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 46, 5));

                level.playSound(null, player.getOnPos(), SoundEvents.END_PORTAL_SPAWN, SoundSource.AMBIENT, 1.5F, level.random.nextFloat() * 0.1f + 0.9F);
                player.die(DamageSource.MAGIC);
            }
            outputRandomNumber();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void startSeenByPlayer(@NotNull ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossEvent.addPlayer(serverPlayer);
    }

    public void stopSeenByPlayer(@NotNull ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossEvent.removePlayer(serverPlayer);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void outputRandomNumber() {
        LOGGER.info("MikuEvent || Boss Random Event: " + getRandomNumber());
    }

    private int getRandomNumber() {
        return RandomSource.createNewThreadLocalInstance().nextInt(10);
    }


    private boolean isNight() {
        return this.level.isNight();
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();

            this.getAttackSound();

            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
