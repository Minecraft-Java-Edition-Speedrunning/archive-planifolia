package dev.tildejustin.planifolia.mixin.universal;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SliderWidget.class)
public abstract class SliderWidgetMixin {
    @Dynamic
    @ModifyExpressionValue(
            method = {
                    /* 1.20.3+ */ "renderWidget",
                    /* 1.20-1.20.2 */ "Lnet/minecraft/class_357;method_48579(Lnet/minecraft/class_332;IIF)V",
                    /* 1.19.4 */ "Lnet/minecraft/class_357;method_48579(Lnet/minecraft/class_4587;IIF)V",
                    /* 1.19-1.19.3 */ "Lnet/minecraft/class_357;method_25353(Lnet/minecraft/class_4587;Lnet/minecraft/class_310;II)V"
            }, at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/widget/SliderWidget;value:D"), require = 1, allow = 2
    )
    private double keepSliderInBounds(double original) {
        return MathHelper.clamp(original, 0, 1);
    }
}
