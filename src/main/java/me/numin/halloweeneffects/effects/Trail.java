package me.numin.halloweeneffects.effects;

import me.numin.halloweeneffects.effects.trails.PumpkinHead;
import me.numin.halloweeneffects.effects.trails.SpookyBats;
import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class Trail implements Effect {

    public static HashMap<Player, Trail> trails = new HashMap<>();
    public Player player;

    public enum Trails {
        PumpkinHead, SpookyBats;
    }

    public Trail(Player player) {
        this.player = player;
        trails.put(player, this);
    }

    public static void removeInstance(Player player) {
        if (!trails.containsKey(player)) return;
        Trail trail = trails.get(player);

        // Terminate pumpkin heads
        if (trail instanceof PumpkinHead) {
            ((PumpkinHead) trail).removePumpkinHead();
        }

        // Terminates living bats
        if (trail instanceof SpookyBats) {
            ((SpookyBats) trail).killAllBats();
        }

        trails.remove(player);
    }
}
