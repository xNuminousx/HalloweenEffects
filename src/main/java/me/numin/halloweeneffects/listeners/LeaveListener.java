package me.numin.halloweeneffects.listeners;

import me.numin.halloweeneffects.effects.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!Effect.effects.containsKey(player)) return;
        Effect.removeInstance(player);
    }
}
