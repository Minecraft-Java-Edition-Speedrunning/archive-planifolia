package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer;
import net.caffeinemc.mods.sodium.client.render.chunk.ChunkRenderMatrices;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SodiumWorldRenderer.class)
public class SodiumWorldRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "drawChunkLayer", at = @At("HEAD"), remap = false)
    private void startProfiler(RenderLayer renderLayer, ChunkRenderMatrices matrices, double x, double y, double z, CallbackInfo ci) {
        this.client.getProfiler().push("filterempty");
        this.client.getProfiler().swap(() -> "render_" + renderLayer);
    }

    @Inject(method = "drawChunkLayer", at = @At("TAIL"), remap = false)
    private void endProfiler(CallbackInfo ci) {
        this.client.getProfiler().pop();
    }
}
