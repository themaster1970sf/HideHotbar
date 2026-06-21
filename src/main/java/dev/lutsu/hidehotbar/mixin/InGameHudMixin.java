package dev.lutsu.hidehotbar.mixin;

import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
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

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void onRenderHotbar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        // Hides hotbar only, keeping shown hearts, food etc
        if (ToolBarConfig.DEV_OPTIONS.hide_hotbar_v1) {
            ci.cancel();
        }
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private static void onRenderArmor(DrawContext context, PlayerEntity player, int i, int j, int k, int x, CallbackInfo ci) {
        // Hides hotbar only, keeping shown hearts, food etc
        if (ToolBarConfig.DEV_OPTIONS.hide_armor_v1) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    private void onRenderHealthBar(DrawContext context, PlayerEntity player,
                                   int x, int y, int lines, int regeneratingHeartIndex,
                                   float maxHealth, int lastHealth, int health, int absorption,
                                   boolean blinking, CallbackInfo ci) {
        // Hides hotbar only, keeping shown hearts, food etc
        if (ToolBarConfig.DEV_OPTIONS.hide_healthbar_v1) {
            ci.cancel();
        }
    }

    @Inject(method = "renderAirBubbles", at = @At("HEAD"), cancellable = true)
    private void onRenderAirBubbles(DrawContext context, PlayerEntity player,
                                    int heartCount, int top, int left, CallbackInfo ci) {
        // Hides hotbar only, keeping shown hearts, food etc
        if (ToolBarConfig.DEV_OPTIONS.hide_bubbles_v1) {
            ci.cancel();
        }
    }

    @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
    private void onRenderFood(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        // Hides hotbar only, keeping shown hearts, food etc
        if (ToolBarConfig.DEV_OPTIONS.hide_food_v1) {
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