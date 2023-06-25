package uk.jamieisgeek.waterrising;

import org.bukkit.configuration.Configuration;

public class ConfigHandler {
    private final WaterRising plugin;
    private Configuration config;

    public ConfigHandler(WaterRising plugin) {
        this.plugin = plugin;
        this.initialize();
    }

    public void initialize() {
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();

    }

    public Object getFromConfig(String path) {
        return this.config.get(path);
    }

    public Long getInterval() {
        return this.config.getLong("interval");
    }

    public Integer getInt(String path) {
        return this.config.getInt(path);
    }
}