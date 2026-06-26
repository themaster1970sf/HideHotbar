package dev.lutsu.hidehotbar;

import dev.lutsu.hidehotbar.config.MinecraftVersion;
import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class HideHotbarModClient implements ClientModInitializer {
    public KeyBinding toggleHudKeyBinding;

    @Override
    public void onInitializeClient() {
        HideHotbarMod.currentVersion = MinecraftVersion.MC_1_21_1;
        toggleHudKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.hidehotbar.toggle_hud",
                GLFW.GLFW_KEY_F7,
                "key.categories.hidehotbar"
        ));

        if (ToolBarConfig.INSTANCE.unhide_on_restart) {
            ToolBarConfig.INSTANCE.hid = false;
        }
        ClientTickEvents.END_CLIENT_TICK.register(this::clientTickListener);
    }

    protected void clientTickListener(MinecraftClient client){
        if (!ToolBarConfig.INSTANCE.enabled) { // skip button press when disabled
            DisableHotbarAlert(client);
            return;
        }

        while (toggleHudKeyBinding.wasPressed()) {
            toggleHotbar(client);
        }

        if (client.player != null){
            float hpPercentage = client.player.getHealth() / client.player.getMaxHealth() * 100;

            if (hpPercentage < ToolBarConfig.INSTANCE.low_hp_percentage
                    && ToolBarConfig.INSTANCE.unhide_on_low_hp
                    && ToolBarConfig.INSTANCE.hid) {
                toggleHotbar(client);
            }

            var foodManager = client.player.getHungerManager();
            float hungerPercentage = (float) foodManager.getFoodLevel() / 20 *100;
            if (hungerPercentage < ToolBarConfig.INSTANCE.low_food_percentage
                    && ToolBarConfig.INSTANCE.unhide_on_low_food
                    && ToolBarConfig.INSTANCE.hid) {
                toggleHotbar(client);
            }
        }
    }

    public static void toggleHotbar(MinecraftClient client) {
        ToolBarConfig.INSTANCE.hid = !ToolBarConfig.INSTANCE.hid;
        SendHotbarAlert(client);
    }

    protected static void SendHotbarAlert(MinecraftClient client) {
        if (client.player != null) {
            client.player.sendMessage(Text.translatable(
                    ToolBarConfig.INSTANCE.hid
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