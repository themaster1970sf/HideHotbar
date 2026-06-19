package dev.lutsu.hidehotbar;

import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

public class HideHotbarModClient implements ClientModInitializer {
    public KeyBinding toggleHudKeyBinding;
    protected float previousHungerLevel;

    @Override
    public void onInitializeClient() {
        toggleHudKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.hidehotbar.toggle_hud",
                GLFW.GLFW_KEY_F7,
                "key.categories.hidehotbar"
        ));

        if (ToolBarConfig.unhide_on_restart) {
            ToolBarConfig.hid = false;
        }
        ClientTickEvents.END_CLIENT_TICK.register(this::clientTickListener);
    }

    protected void clientTickListener(MinecraftClient client){
        if (!ToolBarConfig.enabled) { // skip button press when disabled
            DisableHotbarAlert(client);
            return;
        }

        while (toggleHudKeyBinding.wasPressed()) {
            toggleHotbar(client);
        }

        if (client.player != null){
            float hpPercentage = client.player.getHealth() / client.player.getMaxHealth() * 100;

            if (hpPercentage < ToolBarConfig.low_hp_percentage
                    && ToolBarConfig.unhide_on_low_hp
                    && ToolBarConfig.hid) {
                toggleHotbar(client);
            }

            var foodManager = client.player.getHungerManager();
            float hungerPercentage = (float) foodManager.getFoodLevel() / 20 *100;
            if (hungerPercentage < ToolBarConfig.low_food_percentage
                    && ToolBarConfig.unhide_on_low_food
                    && ToolBarConfig.hid) {
                toggleHotbar(client);
            }
        }
    }

    public static void toggleHotbar(MinecraftClient client) {
        ToolBarConfig.hid = !ToolBarConfig.hid;
        SendHotbarAlert(client);
    }

    protected static void SendHotbarAlert(MinecraftClient client) {
        if (client.player != null) {
            client.player.sendMessage(Text.translatable(
                    ToolBarConfig.hid
                            ? "hidehotbar.hud_hidden"
                            : "hidehotbar.hud_shown"
            ), true);
        }
    }

    protected void DisableHotbarAlert(MinecraftClient client) {
        if (client.player != null) {
            client.player.sendMessage(Text.translatable("hidehotbar.disabled"), true);
        }
    }
}