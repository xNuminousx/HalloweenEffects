package me.numin.halloweeneffects.listeners;

import me.numin.halloweeneffects.effects.Trail;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!Trail.trails.containsKey(player)) return;
        Trail.removeInstance(player);
    }
}
