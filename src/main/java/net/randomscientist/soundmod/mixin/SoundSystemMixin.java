package net.randomscientist.soundmod.mixin;

import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.util.Identifier;
import net.randomscientist.soundmod.ExampleMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(SoundSystem.class)
public class SoundSystemMixin {
    @Shadow private boolean started;
    @Inject(at = @At("HEAD"), method = "play(Lnet/minecraft/client/sound/SoundInstance;)V")
    private void init(SoundInstance sound, CallbackInfo ci) {
        if(this.started) {
            Identifier id = sound.getId();
            double x = sound.getX();
            double y = sound.getY();
            double z = sound.getZ();
            boolean s = sound.shouldAlwaysPlay();
            ExampleMod.LOGGER.info("playing the sound: {} at x: {}, y: {}, z: {}, and it should always play: {}", id,x,y,z,s);
        }
    }
}
