package dev.tildejustin.planifolia.mixin;

import dev.tildejustin.planifolia.mixin.accessor.SodiumOptionsGUIAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin {
    @Dynamic
    @Group(min = 1, max = 1)
    @Inject(method = "method_19828()Lnet/minecraft/class_437;", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private void openVanillaMenu(CallbackInfoReturnable<Screen> cir) {
        if (MinecraftClient.getInstance().world == null) {
            cir.setReturnValue(SodiumOptionsGUIAccessor.newSodiumOptionsGUI((Screen) (Object) this));
        }
    }

    @Dynamic
    @Group
    @ModifyArg(
            method = "method_19828(Lnet/minecraft/class_4185;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", remap = true),
            remap = false, require = 0
    )
    private Screen openVanillaMenu2(Screen original) {
        return MinecraftClient.getInstance().world == null ? SodiumOptionsGUIAccessor.newSodiumOptionsGUI((Screen) (Object) this) : original;
    }
}
