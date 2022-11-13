package com.petey.roll_frames.mixin;

import com.petey.roll_frames.IRollable;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin implements IRollable {

    private int rollingFrames;

    @Override
    public int getRollingFrames() {
        return rollingFrames;
    }

    @Override
    public void setRollingFrames(int frames) {
        rollingFrames = frames;
    }

    @Inject(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
    private void roll_frames_hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
//        RollFrames.LOGGER.info("Hurting player");
        if(rollingFrames > 0) {
//            RollFrames.LOGGER.info("Roll frames > 0");
            if(source instanceof EntityDamageSource && !source.isExplosion()) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "tick()V", at = @At("HEAD"))
    private void roll_frames_tick(CallbackInfo ci) {
        if(rollingFrames > 0) rollingFrames--;
    }
}
