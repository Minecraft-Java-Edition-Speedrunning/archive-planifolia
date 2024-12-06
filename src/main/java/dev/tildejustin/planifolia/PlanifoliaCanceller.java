package dev.tildejustin.planifolia;

import com.bawnorton.mixinsquared.api.MixinCanceller;
import net.fabricmc.loader.api.FabricLoader;

import java.util.*;

public class PlanifoliaCanceller implements MixinCanceller {
    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return Arrays.asList(
                "me.jellysquid.mods.sodium.mixin.features.options.MixinOptionsScreen"
        ).contains(mixinClassName) || Arrays.asList(
                "net.fabricmc.fabric.mixin.resource.loader.MainMixin",
                "net.fabricmc.fabric.mixin.resource.loader.client.CreateWorldScreenMixin"
        ).contains(mixinClassName) && !FabricLoader.getInstance().isModLoaded("fabric");
    }
}
