package dev.lutsu.hidehotbar.mixin;

import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.bar.Bar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bar.class)
public interface BarInterfaceMixin {
    @Inject(method = "drawExperienceLevel", at = @At("HEAD"), cancellable = true)
    private static void onDrawExperienceLevel(DrawContext context, TextRenderer textRenderer, int level, CallbackInfo ci) {
        if (ToolBarConfig.INSTANCE.hid && ToolBarConfig.INSTANCE.hide_expbar) {
            ci.cancel();
        }
    }
}