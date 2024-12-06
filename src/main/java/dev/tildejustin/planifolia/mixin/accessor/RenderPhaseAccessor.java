package dev.tildejustin.planifolia.mixin.accessor;

import net.minecraft.client.render.RenderPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = RenderPhase.class, remap = false)
public interface RenderPhaseAccessor {
    @Accessor("name")
    String getName();
}
