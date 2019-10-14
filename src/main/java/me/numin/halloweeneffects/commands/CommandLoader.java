package me.numin.halloweeneffects.commands;

import me.numin.halloweeneffects.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandLoader implements CommandExecutor {

    public static HashMap<String, Long> pumpkinStrikeCooldowns = new HashMap<>();
    private Player player;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("he") || label.equalsIgnoreCase("halloweeneffects")) {

            if (args.length == 0) new HelpCommand(sender);

            else {

                if (!(sender instanceof Player)) sender.sendMessage("You must be a player to use this command.");
                else player = (Player)sender;

                // Get command
                if (args[0].equalsIgnoreCase("get")) {
                    if (args.length == 2) {
                        new GetCommand(player, args[1]);
                    } else {
                        new GetCommand(player);
                    }
                }

                // List command
                if (args[0].equalsIgnoreCase("list")) {
                    new ListCommand(player);
                }

                // Remove command
                if (args[0].equalsIgnoreCase("remove")) {
                    if (args.length == 2) {
                        new RemoveCommand(player, args[1]);
                    } else {
                        new RemoveCommand(player);
                    }
                }

                // Set command
                if (args[0].equalsIgnoreCase("set")) {
                    if (args.length > 2) {
                        new SetCommand(player, args[1], args[2]);
                    } else {
                        new SetCommand(player, args[1]);
                    }
                }
            }
        }
        return true;
    }
}
