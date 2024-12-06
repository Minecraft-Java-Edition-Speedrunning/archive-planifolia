package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SodiumWorldRenderer.class, remap = false)
public class SodiumWorldRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "drawChunkLayer", at = @At("HEAD"))
    private void startProfiler(CallbackInfo ci, @Local(argsOnly = true) RenderLayer renderLayer) {
        this.client.getProfiler().push("filterempty");
        this.client.getProfiler().swap(() -> "render_" + renderLayer);
    }

    @Inject(method = "drawChunkLayer", at = @At("TAIL"))
    private void endProfiler(CallbackInfo ci) {
        this.client.getProfiler().pop();
    }
}
