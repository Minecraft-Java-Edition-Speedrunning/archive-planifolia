package dev.tildejustin.planifolia.mixin;

import me.jellysquid.mods.sodium.client.render.chunk.RenderSectionManager;
import net.fabricmc.loader.api.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderSectionManager.class)
public class RenderSectionManagerMixin {
    @Shadow
    @Final
    private ClientWorld world;

    @Inject(method = "getTotalSections", at = @At("HEAD"), cancellable = true)
    private void fixTotalSectionCount1_21(CallbackInfoReturnable<Integer> cir) {
        FabricLoader.getInstance().getModContainer("minecraft").ifPresent(minecraft -> {
            try {
                if (minecraft.getMetadata().getVersion().compareTo(Version.parse("1.21")) >= 0) {
                    int renderDistance = MinecraftClient.getInstance().options.getClampedViewDistance() * 2 + 1;
                    cir.setReturnValue(renderDistance * this.world.countVerticalSections() * renderDistance);
                }
            } catch (VersionParsingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
