package me.numin.halloweeneffects.commands;

import me.numin.halloweeneffects.MessageHandler;
import me.numin.halloweeneffects.effects.Effect;
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
            if (Effect.effects.containsKey(target)) {
                sender.sendMessage(MessageHandler.removeSomeonesEffect(Effect.effects.get(target).getName(), target.getName()));
                target.sendMessage(MessageHandler.someoneRemovedEffect(Effect.effects.get(target).getName()));
                Effect.removeInstance(target);
            } else sender.sendMessage(MessageHandler.playerDoesNotHaveAnEffect(target.getName()));
        }
    }

    RemoveCommand(Player player) {
        if (Effect.effects.containsKey(player)) {
            player.sendMessage(MessageHandler.removeEffect(Effect.effects.get(player).getName()));
            Effect.removeInstance(player);
        } else player.sendMessage(MessageHandler.senderDoesNotHaveAnEffect());
    }
}
