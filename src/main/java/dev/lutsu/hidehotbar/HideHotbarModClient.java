package dev.lutsu.hidehotbar;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class HideHotbarModClient implements ClientModInitializer {

    public static KeyBinding toggleHudKeyBinding;
    private static boolean hotbarHidden = false;

    @Override
    public void onInitializeClient() {
        toggleHudKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.hidehotbar.toggle_hud",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F7,
                KeyBinding.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleHudKeyBinding.wasPressed()) {
                hotbarHidden = !hotbarHidden;
                if (client.player != null) {
                    client.player.sendMessage(Text.literal(hotbarHidden ? "Хотбар скрыт" : "Хотбар отображается"), true);
                }
            }
        });
    }

    public static boolean isHotbarHidden() {
        return hotbarHidden;
    }
}