package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import me.jellysquid.mods.sodium.client.render.occlusion.BlockOcclusionCache;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockOcclusionCache.class, targets = "me.jellysquid.mods.sodium.client.render.chunk.compile.pipeline.BlockOcclusionCache", remap = false)
public class BlockOcclusionCacheMixin {
    @Dynamic
    @Inject(
            method = {"shouldDrawSide", "shouldDrawSide(Lnet/minecraft/class_2680;Lnet/minecraft/class_1922;Lnet/minecraft/class_2338;Lnet/minecraft/class_2350;)Z"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isSideInvisible(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z", remap = true),
            cancellable = true, require = 1
    )
    private void adjStateNullCheck(CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 1) BlockState adjState) {
        if (adjState == null) {
            cir.setReturnValue(false);
        }
    }
}
