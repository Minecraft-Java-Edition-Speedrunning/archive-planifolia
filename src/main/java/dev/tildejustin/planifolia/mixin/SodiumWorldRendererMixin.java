package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.tildejustin.planifolia.mixin.accessor.RenderPhaseAccessor;
import net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer;
import net.caffeinemc.mods.sodium.client.render.chunk.ChunkRenderMatrices;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.profiler.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SodiumWorldRenderer.class)
public class SodiumWorldRendererMixin {
    @SuppressWarnings("resource")
    @Inject(method = "drawChunkLayer", at = @At("HEAD"), remap = false)
    private void startProfiler(RenderLayer renderLayer, ChunkRenderMatrices matrices, double x, double y, double z, CallbackInfo ci, @Share("scopedProfiler") LocalRef<ScopedProfiler> scopedProfiler) {
        scopedProfiler.set(Profilers.get().scoped(() -> "render_" + ((RenderPhaseAccessor) renderLayer).getName()));
        scopedProfiler.get().addLabel(renderLayer::toString);
    }

    @Inject(method = "drawChunkLayer", at = @At("TAIL"), remap = false)
    private void endProfiler(CallbackInfo ci, @Share("scopedProfiler") LocalRef<ScopedProfiler> scopedProfiler) {
        scopedProfiler.get().close();
    }
}
