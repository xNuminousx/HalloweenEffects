package me.numin.halloweeneffects.commands;

import me.numin.halloweeneffects.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

class HelpCommand {

    HelpCommand(CommandSender sender) {
        if (sender == null) return;

        sender.sendMessage(MessageHandler.header());
        sender.sendMessage(ChatColor.DARK_PURPLE + "/halloweeneffects (/he) " +
                ChatColor.RESET +
                ChatColor.GOLD + "The main help command.");

        sender.sendMessage(ChatColor.DARK_PURPLE + "/he set <effect name> " +
                ChatColor.RESET +
                ChatColor.GOLD + "Sets a certain effect. Use /he list for a list of acceptable names.");

        sender.sendMessage(ChatColor.DARK_PURPLE + "/he remove " +
                ChatColor.RESET +
                ChatColor.GOLD + "Removes your active effect.");

        sender.sendMessage(ChatColor.DARK_PURPLE + "/he list " +
                ChatColor.RESET +
                ChatColor.GOLD + "Lists all of the enabled effects.");

        sender.sendMessage(MessageHandler.footer());


    }
}
