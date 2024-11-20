package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.gui.SodiumOptionsGUI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin {
    @Inject(method = "method_19828", at = @At("HEAD"), cancellable = true, remap = false)
    private void openVanillaMenu(CallbackInfoReturnable<Screen> cir) {
        if (MinecraftClient.getInstance().world == null) {
            cir.setReturnValue(SodiumOptionsGUI.createScreen((Screen) (Object) this));
        }
    }
}
