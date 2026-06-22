package dev.lutsu.hidehotbar;

import dev.lutsu.hidehotbar.config.ToolBarConfig;
import net.fabricmc.api.ModInitializer;

public class HideHotbarMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ToolBarConfig.load();
    }
}