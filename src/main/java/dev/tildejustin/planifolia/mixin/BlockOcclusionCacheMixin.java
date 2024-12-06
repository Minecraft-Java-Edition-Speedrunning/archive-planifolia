package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockOcclusionCache;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockOcclusionCache.class, remap = false)
public class BlockOcclusionCacheMixin {
    @Inject(method = "shouldDrawSide", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isSideInvisible(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z", remap = true), cancellable = true)
    private void adjStateNullCheck(CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 1) BlockState adjState) {
        if (adjState == null) {
            cir.setReturnValue(false);
        }
    }
}
