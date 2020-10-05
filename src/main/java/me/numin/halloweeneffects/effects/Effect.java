package me.numin.halloweeneffects.effects;

import me.numin.halloweeneffects.effects.effects.PumpkinHead;
import me.numin.halloweeneffects.effects.effects.SpookyBats;
import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class Effect implements IEffect {

    public static HashMap<Player, Effect> effects = new HashMap<>();
    public Player player;
    protected String baseNode = "halloween.effect.";

    public enum Effects {
        PumpkinHead, SpookyBats, ScaredWet
    }

    public Effect(Player player) {
        this.player = player;
        effects.put(player, this);
    }

    public static void removeInstance(Player player) {
        if (!effects.containsKey(player)) return;
        Effect effect = effects.get(player);

        // Terminate pumpkin heads
        if (effect instanceof PumpkinHead) ((PumpkinHead) effect).removePumpkinHead();

        // Terminates living bats
        if (effect instanceof SpookyBats) ((SpookyBats) effect).killAllBats();

        effects.remove(player);
    }
}
