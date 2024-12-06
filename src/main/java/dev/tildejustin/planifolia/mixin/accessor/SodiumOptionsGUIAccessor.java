package dev.tildejustin.planifolia.mixin.accessor;

import me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = SodiumOptionsGUI.class, remap = false)
public interface SodiumOptionsGUIAccessor {
    @Invoker(value = "<init>")
    static SodiumOptionsGUI newSodiumOptionsGUI(Screen screen) {
        throw new AssertionError();
    }
}
