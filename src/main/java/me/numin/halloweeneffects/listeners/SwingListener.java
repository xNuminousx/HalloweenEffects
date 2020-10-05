package me.numin.halloweeneffects.listeners;

import com.projectkorra.projectkorra.BendingPlayer;
import me.numin.halloweeneffects.effects.Effect;
import me.numin.halloweeneffects.effects.effects.ScaredWet;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;

public class SwingListener implements Listener {

    @EventHandler
    public void onSwing(PlayerAnimationEvent event) {
        Player player = event.getPlayer();
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

        if (event.isCancelled()) return;

        if (bPlayer != null && bPlayer.getBoundAbility() != null) return;

        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) return;

        if (!Effect.effects.containsKey(player)) return;
        Effect effect = Effect.effects.get(player);

        if (effect instanceof ScaredWet) {
            if (!((ScaredWet) effect).isOnCooldown()) {
                ((ScaredWet) effect).canShoot = true;
                ((ScaredWet) effect).initializeVars = true;
            }
        }
    }
}
