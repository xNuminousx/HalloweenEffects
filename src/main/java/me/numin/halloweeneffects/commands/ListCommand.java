package me.numin.halloweeneffects.commands;

import me.numin.halloweeneffects.MessageHandler;
import me.numin.halloweeneffects.effects.Effect.Effects;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ListCommand {

    ListCommand(Player player) {
        List<Effects> trails = new ArrayList<>(Arrays.asList(Effects.values()));

        player.sendMessage(MessageHandler.header());
        for (Effects trail: trails) {
            int bullet = trails.indexOf(trail) + 1;
            String trailName = trail.toString();
            String description = null;

            if (trailName.equalsIgnoreCase("PumpkinHead"))
                description = "Curses you with the face of the wicked.";
            else if (trailName.equalsIgnoreCase("SpookyBats"))
                description = "Spooky season is upon us... Thus, swarms of spooky bats!";
            else if (trailName.equalsIgnoreCase("ScaredWet"))
                description = "Moisten up the scene with fear! Cooldown: 30s";

            player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + bullet + ") " +
                    ChatColor.GOLD + "" + ChatColor.BOLD + trailName + ": " +
                    ChatColor.DARK_GRAY + description);
        }
    }
}