package me.numin.halloweeneffects.commands;

import me.numin.halloweeneffects.MessageHandler;
import me.numin.halloweeneffects.effects.Effect;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

class GetCommand {

    GetCommand(Player sender, String input) {
        if (!sender.hasPermission("halloween.command.op")) {
            sender.sendMessage(MessageHandler.noPermission());
            return;
        }

        Player target = Bukkit.getPlayer(input);
        if (target == null) {
            sender.sendMessage(MessageHandler.playerDoesNotExist(input));
        }

        assert target != null;
        if (Effect.effects.containsKey(target)) {
            String trailName = Effect.effects.get(target).getName();

            sender.sendMessage(MessageHandler.header());
            sender.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + target.getName() + " " +
                    ChatColor.GOLD + trailName);
        } else {
            sender.sendMessage(MessageHandler.playerDoesNotHaveAnEffect(target.getName()));
        }
    }

    GetCommand(Player player) {
        if (player == null) return;

        if (Effect.effects.containsKey(player)) {
            String trailName = Effect.effects.get(player).getName();

            player.sendMessage(MessageHandler.header());
            player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You " +
                    ChatColor.GOLD + trailName);
        } else {
            player.sendMessage(MessageHandler.senderDoesNotHaveAnEffect());
        }
    }
}
