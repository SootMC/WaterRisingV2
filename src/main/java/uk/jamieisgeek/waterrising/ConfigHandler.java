package uk.jamieisgeek.waterrising;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigHandler {
    private final WaterRising plugin;
    private Configuration config;

    public ConfigHandler(WaterRising plugin) {
        this.plugin = plugin;
    }

    public boolean initialize() {
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();

        return true;
    }

    public Object getFromConfig(String path) {
        return config.get(path);
    }
}