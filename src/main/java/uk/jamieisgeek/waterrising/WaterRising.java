package uk.jamieisgeek.waterrising;

import org.bukkit.plugin.java.JavaPlugin;

public final class WaterRising extends JavaPlugin {
    private ConfigHandler configHandler;
    private WaterRiser waterRiser;

    @Override
    public void onEnable() {
        this.configHandler = new ConfigHandler(this);
        this.waterRiser = new WaterRiser(configHandler);

        getCommand("event").setExecutor(new Command(waterRiser, configHandler));
        getLogger().info("WaterRising enabled!");
    }

    @Override
    public void onDisable() {
        waterRiser.cancel();
        getLogger().info("WaterRising disabled!");
    }
}
