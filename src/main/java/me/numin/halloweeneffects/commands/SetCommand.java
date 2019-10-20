package me.numin.halloweeneffects.commands;

import me.numin.halloweeneffects.MessageHandler;
import me.numin.halloweeneffects.effects.Trail;
import me.numin.halloweeneffects.effects.Trail.Trails;
import me.numin.halloweeneffects.effects.trails.PumpkinHead;
import me.numin.halloweeneffects.effects.trails.SpookyBats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class SetCommand {

    private String trailName;

    SetCommand(Player player, String input) {
        if (player == null) return;

        if (player.getWorld().getName().equals("bendarenas")) {
            player.sendMessage(MessageHandler.illegalWorld(null));
            return;
        }

        if (input == null) {
            player.sendMessage(MessageHandler.invalidArgument());
            return;
        }

        // Creates a list of trail names to be compared.
        List<String> trailNames = new ArrayList<>();
        for (Trails trailType : Trails.values()) {
            trailNames.add(trailType.toString());
        }

        for (Iterator<String> iterator = trailNames.iterator(); iterator.hasNext();) {
            String item = iterator.next();

            if (item.equalsIgnoreCase(input)) {

                // Checks the player for active effects. Removes them if any exist.
                if (Trail.trails.containsKey(player)){
                    Trail trail = Trail.trails.get(player);
                    if (trail.getName().equalsIgnoreCase(item)) {
                        player.sendMessage(MessageHandler.effectAlreadyActive());
                        return;
                    } else Trail.removeInstance(player);
                }

                initializeEffect(player, input);
                player.sendMessage(MessageHandler.newEffect(trailName));
                break;
            } else if (!iterator.hasNext()) player.sendMessage(MessageHandler.effectDoesNotExist(input));
        }
    }

    SetCommand(Player sender, String input, String target) {
        if (sender == null) return;

        if (input == null) {
            sender.sendMessage(MessageHandler.invalidArgument());
            return;
        }

        if (!sender.hasPermission("halloween.command.op")) {
            sender.sendMessage(MessageHandler.noPermission());
            return;
        }

        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer == null) {
            sender.sendMessage(MessageHandler.playerDoesNotExist(target));
        } else {
            if (targetPlayer.getWorld().getName().equals("bendarenas")) {
                sender.sendMessage(MessageHandler.illegalWorld(targetPlayer));
                return;
            }

            List<String> trailNames = new ArrayList<>();
            for (Trails trailType : Trails.values()) {
                trailNames.add(trailType.toString());
            }

            for (Iterator<String> iterator = trailNames.iterator(); iterator.hasNext();) {
                String item = iterator.next();

                if (item.equalsIgnoreCase(input)) {

                    // Checks the target for active effects. Removes them if any exist.
                    if (Trail.trails.containsKey(targetPlayer)) {
                        Trail trail = Trail.trails.get(targetPlayer);
                        if (trail.getName().equalsIgnoreCase(item)) {
                            targetPlayer.sendMessage(MessageHandler.someoneElseEffectAlreadyActive());
                            return;
                        } else Trail.removeInstance(targetPlayer);
                    }

                    initializeEffect(targetPlayer, input);
                    targetPlayer.sendMessage(MessageHandler.newEffect(trailName));
                    sender.sendMessage(MessageHandler.newEffectSomeoneElse(trailName, targetPlayer.getName()));
                    break;
                } else if (!iterator.hasNext()) sender.sendMessage(MessageHandler.effectDoesNotExist(input));
            }
        }
    }

    private void initializeEffect(Player player, String name) {
        if (name.equalsIgnoreCase("SpookyBats")) {
            new SpookyBats(player);
            trailName = "SpookyBats";
        } else if (name.equalsIgnoreCase("PumpkinHead")) {
            new PumpkinHead(player);
            trailName = "PumpkinHead";
        }
    }
}
