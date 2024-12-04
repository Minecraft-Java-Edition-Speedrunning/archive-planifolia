package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.google.common.collect.Lists;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(value = DebugHud.class, priority = 1050)
public abstract class DebugHudMixin {
    @Dynamic
    @Invoker(value = "getVersionColor", remap = false)
    public static Formatting callGetVersionColor() {
        throw new RuntimeException();
    }

    @Dynamic
    @TargetHandler(mixin = "net.fabricmc.fabric.mixin.renderer.client.debughud.DebugHudMixin", name = "getLeftText")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
    private void getLeftText(CallbackInfo ci) {
        if (!FabricLoader.getInstance().isModLoaded("fabric")) {
            ci.cancel();
        }
    }

    @Dynamic
    @TargetHandler(mixin = "net.caffeinemc.mods.sodium.mixin.features.gui.hooks.debug.DebugScreenOverlayMixin", name = "redirectRightTextEarly")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
    private void cancelSodiumTextAddition(Object[] elements, CallbackInfoReturnable<ArrayList<Object>> cir) {
        cir.setReturnValue(Lists.newArrayList(elements));
    }

    @Inject(method = "getRightText", at = @At(value = "RETURN"))
    private void redoSodiumTextAddition(CallbackInfoReturnable<List<String>> cir) {
        List<String> list = cir.getReturnValue();
        list.add("");
        list.add(String.format("%sSodium Renderer (%s)", callGetVersionColor(), SodiumClientMod.getVersion()));
    }
}
