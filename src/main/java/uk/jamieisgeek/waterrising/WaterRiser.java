package uk.jamieisgeek.waterrising;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
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
        if (currentLevel >= (int) configHandler.getFromConfig("max-level")) {
            cancel();
        }

        BlockVector3 side1 = getBlockVector3(new Vector((int) configHandler.getFromConfig("corner1.x"), currentLevel, (int) configHandler.getFromConfig("corner1.z")));
        BlockVector3 side2 = getBlockVector3(new Vector((int) configHandler.getFromConfig("corner2.x"), currentLevel, (int) configHandler.getFromConfig("corner2.z")));
        Region region = new CuboidRegion(side1, side2);

        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(region.getWorld(), -1);

        BlockType blockType = BlockTypes.WATER;
        Pattern pattern = blockType.getDefaultState();
        editSession.setBlocks(region, pattern);

        editSession.close();

        Bukkit.broadcast(new TextManager().translateHex("&#266c97&lThe water is at Y: " + currentLevel), "waterrising.notify");
    }

    private BlockVector3 getBlockVector3(Vector vector) {
        return BlockVector3.at(vector.getX(), vector.getY(), vector.getZ());
    }

    public boolean isRunning() {
        return this.isCancelled();
    }
}
