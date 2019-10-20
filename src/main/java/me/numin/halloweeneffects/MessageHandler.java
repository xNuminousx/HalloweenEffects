package me.numin.halloweeneffects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Random;

public class MessageHandler {

    public static String header() {
        return ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Mystic Empire" +
                ChatColor.GOLD + " - " +
                ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Halloween Effects";
    }
    public static String footer() {
        return ChatColor.GRAY + "" + ChatColor.ITALIC + "Author: Numin";
    }
    public static String lineHeader() {
        return ChatColor.BLACK + "" + ChatColor.BOLD + "[" +
                ChatColor.RESET +
                ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "MEHE" +
                ChatColor.BLACK + "" + ChatColor.BOLD + "] ";
    }
    public static String illegalWorld(Player target) {
        if (target == null) return lineHeader() + ChatColor.RED + "You're in a disabled world.";
        else return lineHeader() + ChatColor.RED + "That player is in a disabled world.";
    }
    public static String invalidArgument() {
        return lineHeader() + ChatColor.RED + "You've inputted the command incorrectly. Use " + ChatColor.ITALIC + "/he" +
                ChatColor.RESET +
                ChatColor.RED + " to get a list of commands and their usages.";
    }
    public static String tooManyArguments() {
        return lineHeader() + ChatColor.RED + "You've given too many arguments. Use " + ChatColor.ITALIC + "/he" +
                ChatColor.RESET +
                ChatColor.RED + " to get a list of commands and their usages.";
    }
    public static String effectAlreadyActive() {
        return lineHeader() + ChatColor.RED + "You already have that effect active.";
    }
    public static String someoneElseEffectAlreadyActive() {
        return lineHeader() + ChatColor.RED + "They already have that effect active.";
    }
    public static String effectDoesNotExist(String input) {
        return lineHeader() + ChatColor.RED + "The effect '" + input + "' does not exist.";
    }
    public static String playerDoesNotHaveAnEffect(String input) {
        return lineHeader() + ChatColor.RED + "The player '" + input + "' does not have an effect.";
    }
    public static String playerDoesNotExist(String input) {
        return lineHeader() + ChatColor.RED + "The player '" + input + "' does not exist or is not online.";
    }
    public static String senderDoesNotHaveAnEffect() {
        return lineHeader() + ChatColor.RED + "You do not have an effect.";
    }
    public static String newEffect(String input) {
        return lineHeader() + ChatColor.GREEN + "You now have the '" + input + "' effect!";
    }
    public static String newEffectSomeoneElse(String effect, String target) {
        return lineHeader() + ChatColor.GREEN + "You gave " + target + " the " + effect + " effect.";
    }
    public static String removeEffect(String input) {
        return lineHeader() + ChatColor.GREEN + "Removed the " + input + " effect.";
    }
    public static String removeEffectBy(String input, String byName) {
        return lineHeader() + ChatColor.GREEN + "You removed the " + input + " effect by " + byName;
    }
    public static String noPermission() {
        return lineHeader() + ChatColor.RED + "You do not have permission for that.";
    }
    public static String pumpkinThief() {
        int num = new Random().nextInt(3);
        switch(num) {
            case 1: return lineHeader() + ChatColor.GOLD + "Getting ready for Santa's naughty list early?";
            case 2: return lineHeader() + ChatColor.GOLD + "Trying to steal " + ChatColor.BOLD + "PUMPKINS" +
                    ChatColor.RESET + ChatColor.GOLD + "???";
            default: return lineHeader() + ChatColor.GOLD + "" + ChatColor.ITALIC +  "The " + ChatColor.BOLD + "curse of the PumpkinHead " +
                   ChatColor.RESET + ChatColor.GOLD + "" + ChatColor.ITALIC +  "shall forever haunt your being!";
        }
    }
}
