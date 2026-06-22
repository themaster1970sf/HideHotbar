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

    // general
    public static boolean enabled = true;
    public static boolean debug = false;
    public static boolean hid = false;

    // unhide conditions
    public static boolean unhide_on_restart = true;
    public static boolean unhide_on_slot_change = true;
    public static boolean unhide_on_damage = false;
    public static boolean unhide_on_low_hp = false;
    public static boolean unhide_on_low_food = false;
    public static int low_hp_percentage = 20;
    public static int low_food_percentage = 20;

    // customization of hiding
    public static boolean hide_hotbar = true;
    public static boolean hide_armor = true;
    public static boolean hide_healthbar = true;
    public static boolean hide_food = true;
    public static boolean hide_bubbles = true;
    public static boolean hide_expbar = true;
    public static boolean hide_locator = true;

    // other
    public static class DEV_OPTIONS {

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

                hide_hotbar = data.hide_hotbar;
                hide_armor = data.hide_armor;
                hide_healthbar = data.hide_healthbar;
                hide_food = data.hide_food;
                hide_bubbles = data.hide_bubbles;
                hide_expbar = data.hide_expbar;
                hide_locator = data.hide_locator;

                // load dev settings only if debug is on
//                if (debug){
//                    // No dev config for now
//                }
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

        data.hide_hotbar = hide_hotbar;
        data.hide_armor = hide_armor;
        data.hide_healthbar = hide_healthbar;
        data.hide_food = hide_food;
        data.hide_bubbles = hide_bubbles;
        data.hide_expbar = hide_expbar;
        data.hide_locator = hide_locator;

//        if (debug){
//        }

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

        boolean hide_hotbar = true;
        boolean hide_armor = true;
        boolean hide_healthbar = true;
        boolean hide_food = true;
        boolean hide_bubbles = true;
        boolean hide_expbar = true;
        boolean hide_locator = true;

        // dev options bellow

    }
}