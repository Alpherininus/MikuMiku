package org.alpherininus.mikumiku.common.entitys.animated;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class StairsEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(false);
    private final ServerBossEvent bossEventPh2 = (ServerBossEvent)(new ServerBossEvent(Component.translatable(this.getDisplayName() + ".ph2"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

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

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));

        data.addAnimationController(new AnimationController(this, "attackcontroller", 0, this::attackPredicate));
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

    @Override
    protected void customServerAiStep() {
        if (this.isNight()) {
            if (this.tickCount % 20 == 0) {
                this.heal(10.0F);
            }
            this.bossEventPh2.setProgress(this.getHealth() / this.getMaxHealth());
        }
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public void startSeenByPlayer(@NotNull ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);

        if (this.isNight()) {
            this.bossEventPh2.addPlayer(serverPlayer);
        }

        this.bossEvent.addPlayer(serverPlayer);
    }

    public void stopSeenByPlayer(@NotNull ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);

        if (this.isNight()) {
            this.bossEventPh2.addPlayer(serverPlayer);
        }

        this.bossEvent.removePlayer(serverPlayer);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean isNight() {
        return this.level.isNight();
    }
}
