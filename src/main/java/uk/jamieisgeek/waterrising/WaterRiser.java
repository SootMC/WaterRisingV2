package uk.jamieisgeek.waterrising;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import uk.jamieisgeek.sootlib.Misc.TextManager;

public class WaterRiser extends BukkitRunnable {
    private final ConfigHandler configHandler;
    private int currentLevel;
    public WaterRiser(ConfigHandler configHandler) {
        this.configHandler = configHandler;
        this.currentLevel = (int) configHandler.getFromConfig("base-level");
    }

    @Override
    public void run() {
        currentLevel++;

        World world = Bukkit.getWorld((String) configHandler.getFromConfig("world"));
        WorldBorder border = world.getWorldBorder();
        int size = (int) border.getSize();
        int centerX = (int) border.getCenter().getX();
        int centerZ = (int) border.getCenter().getZ();
        int startX = centerX - size / 2;
        int startZ = centerZ - size / 2;
        int endX = centerX + size / 2;
        int endZ = centerZ + size / 2;

        for (int x = startX + 2; x < endX + 2; x++) {
            for (int z = startZ + 2; z < endZ + 2; z++) {
                Block block = world.getBlockAt(x, currentLevel, z);
                if(block.getType() != Material.AIR) continue;
                block.setType(Material.WATER, false);
            }
        }

        Bukkit.broadcast(new TextManager().translateHex("&#266c97&lThe water is at Y: " + currentLevel), "waterrising.notify");
    }
}
