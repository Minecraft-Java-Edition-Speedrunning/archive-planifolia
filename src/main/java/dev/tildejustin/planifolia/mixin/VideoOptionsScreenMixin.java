package dev.tildejustin.planifolia.mixin;

import me.jellysquid.mods.sodium.client.SodiumClientMod;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(VideoOptionsScreen.class)
public abstract class VideoOptionsScreenMixin {
    @Inject(method = "getOptions", at = @At("TAIL"), cancellable = true)
    private static void addEntityCullingOption(GameOptions gameOptions, CallbackInfoReturnable<SimpleOption<?>[]> cir) {
        SimpleOption<?>[] newOptions = Arrays.copyOf(cir.getReturnValue(), cir.getReturnValue().length + 1);
        newOptions[newOptions.length - 1] = SimpleOption.ofBoolean(
                "Entity Culling",
                SodiumClientMod.options().performance.useEntityCulling,
                entityCulling -> SodiumClientMod.options().performance.useEntityCulling = entityCulling
        );
        cir.setReturnValue(newOptions);
    }
}
