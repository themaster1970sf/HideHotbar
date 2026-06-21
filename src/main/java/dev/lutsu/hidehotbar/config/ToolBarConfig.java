package dev.lutsu.hidehotbar.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ToolBarConfig {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_FILE =
            FabricLoader.getInstance().getConfigDir().resolve("hidehotbar.json");

    public static boolean enabled = true;
    public static boolean debug = false;
    public static boolean hid = false;

    public static boolean unhide_on_restart = true;
    public static boolean unhide_on_slot_change = true;
    public static boolean unhide_on_damage = false;
    public static boolean unhide_on_low_hp = false;
    public static boolean unhide_on_low_food = false;
    public static int low_hp_percentage = 20;
    public static int low_food_percentage = 20;

    public static class DEV_OPTIONS {
        public static boolean hide_hotbar_v1 = false;
        public static boolean hide_armor_v1 = false;
        public static boolean hide_healthbar_v1 = false;
        public static boolean hide_food_v1 = false;
        public static boolean hide_bubbles_v1 = false;
    }

    public static void load() {
        if (!Files.exists(CONFIG_FILE)) {
            save();
            return;
        }

        try {
            ConfigData data = GSON.fromJson(
                    Files.readString(CONFIG_FILE),
                    ConfigData.class
            );

            if (data != null) {
                enabled = data.enabled;
                debug = data.debug;
                hid = data.hid;

                unhide_on_restart = data.unhide_on_restart;
                unhide_on_slot_change = data.unhide_on_slot_change;
                unhide_on_damage = data.unhide_on_damage;
                unhide_on_low_hp = data.unhide_on_low_hp;
                unhide_on_low_food = data.unhide_on_low_food;
                low_hp_percentage = data.low_hp_percentage;
                low_food_percentage = data.low_food_percentage;

                // load dev settings only if debug is on
                if (debug){
                    DEV_OPTIONS.hide_hotbar_v1 = data.hide_hotbar_v1;
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public static void save() {
        ConfigData data = new ConfigData();
        data.enabled = enabled;
        data.debug = debug;
        data.hid = hid;

        data.unhide_on_restart = unhide_on_restart;
        data.unhide_on_slot_change = unhide_on_slot_change;
        data.unhide_on_damage = unhide_on_damage;
        data.unhide_on_low_hp = unhide_on_low_hp;
        data.unhide_on_low_food = unhide_on_low_food;
        data.low_hp_percentage = low_hp_percentage;
        data.low_food_percentage = low_food_percentage;

        if (debug){
            data.hide_hotbar_v1 = DEV_OPTIONS.hide_hotbar_v1;
        }

        try {
            Files.writeString(
                    CONFIG_FILE,
                    GSON.toJson(data)
            );
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    private static class ConfigData {
        boolean enabled = true;
        boolean debug = false;
        boolean hid = true;

        boolean unhide_on_restart = true;
        boolean unhide_on_slot_change = true;
        boolean unhide_on_damage = false;
        boolean unhide_on_low_hp = false;
        boolean unhide_on_low_food = false;
        int low_hp_percentage = 20;
        int low_food_percentage = 20;

        boolean hide_hotbar_v1 = false;
    }
}