package dev.lutsu.hidehotbar.mixin;

import dev.lutsu.hidehotbar.HideHotbarModClient;
import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.bar.ExperienceBar;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceBar.class)
public class ExperienceBarMixin {
    @Inject(method = "renderBar", at = @At("HEAD"), cancellable = true)
    private void onRenderBar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (ToolBarConfig.hid) {
            ci.cancel();
        }
    }

    @Inject(method = "renderAddons", at = @At("HEAD"), cancellable = true)
    private void onRenderAddons(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (ToolBarConfig.hid) {
            ci.cancel();
        }
    }
}