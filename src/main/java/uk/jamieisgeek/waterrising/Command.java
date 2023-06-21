package uk.jamieisgeek.waterrising;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    private final WaterRiser waterRiser;
    private final ConfigHandler configHandler;
    public Command(WaterRiser waterRiser, ConfigHandler configHandler) {
        this.waterRiser = waterRiser;
        this.configHandler = configHandler;
    }
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
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
            System.out.println(configHandler.getFromConfig("interval"));
            waterRiser.runTaskTimer(WaterRising.getProvidingPlugin(WaterRising.class), 0, configHandler.getInterval());
            player.sendMessage("The water is now rising!");
            return true;
        } else if (args[0].equalsIgnoreCase("stop")) {
            waterRiser.cancel();
            player.sendMessage("The water is no longer rising!");
            return true;
        }

        return true;
    }
}
