package dev.lutsu.hidehotbar;

import dev.lutsu.hidehotbar.config.MinecraftVersion;import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.fabricmc.api.ModInitializer;

public class HideHotbarMod implements ModInitializer {
    public static MinecraftVersion currentVersion;

    @Override
    public void onInitialize() {
        ToolBarConfig.load();
    }
}