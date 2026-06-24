package dev.lutsu.hidehotbar.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ToolBarConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("hidehotbar.json");

    // The active global instance holding your current configuration values
    public static ToolBarConfig INSTANCE = new ToolBarConfig();

    // general
    public boolean enabled = true;
    public boolean debug = false;
    public boolean hid = false;

    // unhide conditions
    public boolean unhide_on_restart = true;
    public boolean unhide_on_slot_change = true;
    public boolean unhide_on_damage = false;
    public boolean unhide_on_low_hp = false;
    public boolean unhide_on_low_food = false;
    public int low_hp_percentage = 20;
    public int low_food_percentage = 20;

    // customization of hiding
    public boolean hide_hotbar = true;
    public boolean hide_armor = true;
    public boolean hide_healthbar = true;
    public boolean hide_food = true;
    public boolean hide_bubbles = true;
    public boolean hide_expbar = true;
    public boolean hide_locator = true;

    public static void load() {
        if (!Files.exists(CONFIG_FILE)) {
            save();
            return;
        }
        try {
            ToolBarConfig loaded = GSON.fromJson(Files.readString(CONFIG_FILE), ToolBarConfig.class);
            if (loaded != null) INSTANCE = loaded;
        } catch (Exception ignored) {}
    }

    public static void save() {
        try {
            Files.writeString(CONFIG_FILE, GSON.toJson(INSTANCE));
        } catch (IOException ignored) {}
    }
}