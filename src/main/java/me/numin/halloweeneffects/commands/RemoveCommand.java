package me.numin.halloweeneffects.commands;

import me.numin.halloweeneffects.MessageHandler;
import me.numin.halloweeneffects.effects.Trail;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

class RemoveCommand {

    RemoveCommand(Player sender, String input) {
        if (!sender.hasPermission("halloween.command.op")) {
            sender.sendMessage(MessageHandler.noPermission());
            return;
        }

        Player target = Bukkit.getPlayer(input);
        if (target == null) {
            sender.sendMessage(MessageHandler.playerDoesNotExist(input));
        } else {
            if (Trail.trails.containsKey(target)) {
                sender.sendMessage(MessageHandler.removeEffectBy(Trail.trails.get(target).getName(), sender.getName()));
                Trail.removeInstance(target);
            } else sender.sendMessage(MessageHandler.playerDoesNotHaveAnEffect(target.getName()));
        }
    }

    RemoveCommand(Player player) {
        if (Trail.trails.containsKey(player)) {
            player.sendMessage(MessageHandler.removeEffect(Trail.trails.get(player).getName()));
            Trail.removeInstance(player);
        } else player.sendMessage(MessageHandler.senderDoesNotHaveAnEffect());
    }
}
