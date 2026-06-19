package dev.lutsu.hidehotbar.mixin;

import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "renderMainHud", at = @At("HEAD"), cancellable = true)
    private void onRenderMainHud(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (ToolBarConfig.hid) {
            ci.cancel();
        }
    }

    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    private void onRenderStatusBars(DrawContext context, CallbackInfo ci) {
        if (ToolBarConfig.hid) {
            ci.cancel();
        }
    }
}