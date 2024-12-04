package dev.tildejustin.planifolia;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.*;

public class PlanifoliaCanceller implements MixinCanceller {

    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return Arrays.asList(
                "net.fabricmc.fabric.mixin.resource.loader.client.ClientDataPackManagerMixin",
                "net.fabricmc.fabric.mixin.resource.loader.client.CreateWorldScreenMixin"
        ).contains(mixinClassName);
    }
}
