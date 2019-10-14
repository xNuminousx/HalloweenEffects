package me.numin.halloweeneffects.effects;

import org.bukkit.entity.Player;

public interface Effect {
    void run();
    String getName();
    String getPermission();
    Player getPlayer();
}
