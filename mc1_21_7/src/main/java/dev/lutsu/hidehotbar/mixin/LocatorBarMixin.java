package dev.lutsu.hidehotbar.mixin;

import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.bar.LocatorBar;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocatorBar.class)
public class LocatorBarMixin {
    @Inject(method = "renderBar", at = @At("HEAD"), cancellable = true)
    private void onRenderBar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (ToolBarConfig.INSTANCE.hid && ToolBarConfig.INSTANCE.hide_locator) {
            ci.cancel();
        }
    }

    @Inject(method = "renderAddons", at = @At("HEAD"), cancellable = true)
    private void onRenderAddons(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (ToolBarConfig.INSTANCE.hid && ToolBarConfig.INSTANCE.hide_locator) {
            ci.cancel();
        }
    }
}