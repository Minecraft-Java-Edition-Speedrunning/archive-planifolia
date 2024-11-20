package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.render.chunk.RenderSectionManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderSectionManager.class)
public class RenderSectionManagerMixin {
    @Shadow
    @Final
    private ClientWorld level;

    @Inject(method = "getTotalSections", at = @At("HEAD"), cancellable = true, remap = false)
    private void fixTotalSectionCount(CallbackInfoReturnable<Integer> cir) {
        int renderDistance = MinecraftClient.getInstance().options.getClampedViewDistance() * 2 + 1;
        cir.setReturnValue(renderDistance * this.level.countVerticalSections() * renderDistance);
    }
}
