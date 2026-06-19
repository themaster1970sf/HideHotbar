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

    public static final String CATEGORY = "key.categories.hidehotbar";
    public static KeyBinding toggleHudKeyBinding;
    private static boolean hudHidden = false;

    @Override
    public void onInitializeClient() {
        toggleHudKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.hidehotbar.toggle_hud",
            GLFW.GLFW_KEY_F7,
            CATEGORY
        ));

        hudHidden = ToolBarConfig.hid = false;
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleHudKeyBinding.wasPressed()) {
                if (!ToolBarConfig.enabled){ // skip button press when disabled
                    if (client.player != null) {
                        client.player.sendMessage(Text.translatable("hidehotbar.disabled"), true);
                    }
                    return;
                }

                toggleHotbar(client);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            float hpPercentage = client.player.getHealth() / client.player.getMaxHealth() *100;

            if (hpPercentage < ToolBarConfig.low_hp_percentage && isHotbarHidden()){
                toggleHotbar(client);
            }

            var foodManager = client.player.getHungerManager();
            float hungerPercentage = foodManager.getFoodLevel();
        });
    }

    public static void toggleHotbar(@Nullable MinecraftClient client){
        hudHidden = !hudHidden;
        ToolBarConfig.hid = hudHidden;
        if (client != null && client.player != null) {
            client.player.sendMessage(Text.translatable(hudHidden ? "hidehotbar.hud_hidden" : "hidehotbar.hud_shown"), true);
        }
    }

    public static void toggleHotbar(){
        toggleHotbar(null);
    }

    public static boolean isHotbarHidden() {
        return hudHidden;
    }
}