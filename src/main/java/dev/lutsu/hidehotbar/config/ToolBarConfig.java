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
    public static boolean hid = false;

    public static boolean unhide_on_damage = false;

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
                hid = data.hid;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        ConfigData data = new ConfigData();
        data.enabled = enabled;

        try {
            Files.writeString(
                    CONFIG_FILE,
                    GSON.toJson(data)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ConfigData {
        boolean enabled = true;
        boolean hid = true;
    }
}