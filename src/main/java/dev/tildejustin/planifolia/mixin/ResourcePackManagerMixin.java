package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.minecraft.resource.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Set;

@Mixin(value = ResourcePackManager.class, priority = 1050)
public abstract class ResourcePackManagerMixin {
    @Dynamic
    @TargetHandler(mixin = "net.fabricmc.fabric.mixin.resource.loader.ResourcePackManagerMixin", name = "construct")
    @WrapOperation(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"))
    private boolean removeFabricDataPacks(Set<ResourcePackProvider> providers, Object provider, Operation<Boolean> operation) {
        return true;
    }
}
