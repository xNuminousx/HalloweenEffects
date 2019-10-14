package me.numin.halloweeneffects.commands;

import me.numin.halloweeneffects.MessageHandler;
import me.numin.halloweeneffects.effects.Trail.Trails;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ListCommand {

    ListCommand(Player player) {
        List<Trails> trails = new ArrayList<>(Arrays.asList(Trails.values()));

        player.sendMessage(MessageHandler.header());
        for (Trails trail: trails) {
            int bullet = trails.indexOf(trail) + 1;
            String trailName = trail.toString();

            player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + bullet + ") " +
                    ChatColor.RESET +
                    ChatColor.GOLD + trailName);
        }
    }
}
