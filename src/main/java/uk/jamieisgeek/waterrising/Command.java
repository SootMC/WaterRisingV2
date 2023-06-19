package uk.jamieisgeek.waterrising;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {
    private final WaterRiser waterRiser;
    private final ConfigHandler configHandler;
    public Command(WaterRiser waterRiser, ConfigHandler configHandler) {
        this.waterRiser = waterRiser;
        this.configHandler = configHandler;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if(!player.hasPermission("waterrising.admin")) {
            player.sendMessage("You do not have permission to use this command!");
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("Usage: /waterrising <start|stop>");
            return true;
        }

        if(args[0].equalsIgnoreCase("start")) {
            if(waterRiser.isRunning()) {
                player.sendMessage("The water is already rising!");
                return true;
            }

            waterRiser.runTaskTimer(WaterRising.getProvidingPlugin(WaterRising.class), 0, (long) configHandler.getFromConfig("interval"));
            player.sendMessage("The water is now rising!");
            return true;
        } else if (args[0].equalsIgnoreCase("stop")) {
            if(!waterRiser.isRunning()) {
                player.sendMessage("The water is not rising!");
                return true;
            }

            waterRiser.cancel();
            player.sendMessage("The water is no longer rising!");
            return true;
        }

        return true;
    }
}
