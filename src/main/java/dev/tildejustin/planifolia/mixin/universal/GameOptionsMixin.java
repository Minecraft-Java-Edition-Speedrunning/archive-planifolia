package dev.tildejustin.planifolia.mixin.universal;

import dev.tildejustin.planifolia.DoubleSliderCallbacksGamma;
import net.minecraft.client.option.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    // 1.19-1.19.2
    @Dynamic
    @Group(min = 1, max = 1)
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/class_7172;<init>(Ljava/lang/String;Lnet/minecraft/class_7172$class_7307;Lnet/minecraft/class_7172$class_7303;Lnet/minecraft/class_7172$class_7178;Ljava/lang/Object;Ljava/util/function/Consumer;)V",
                    remap = false, ordinal = 0
            ),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=options.gamma")),
            index = 3,
            require = 0
    )
    private SimpleOption.Callbacks<?> replaceGammaSliderCallbackOld(SimpleOption.Callbacks<?> original) {
        return DoubleSliderCallbacksGamma.INSTANCE;
    }

    // 1.19.3+
    @Group
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/SimpleOption;<init>(Ljava/lang/String;Lnet/minecraft/client/option/SimpleOption$TooltipFactory;Lnet/minecraft/client/option/SimpleOption$ValueTextGetter;Lnet/minecraft/client/option/SimpleOption$Callbacks;Ljava/lang/Object;Ljava/util/function/Consumer;)V",
                    ordinal = 0
            ),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=options.gamma")),
            index = 3,
            require = 0
    )
    private SimpleOption.Callbacks<?> replaceGammaSliderCallback(SimpleOption.Callbacks<?> original) {
        return DoubleSliderCallbacksGamma.INSTANCE;
    }
}
