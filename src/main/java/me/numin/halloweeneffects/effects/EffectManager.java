package me.numin.halloweeneffects.effects;

import me.numin.halloweeneffects.HalloweenEffects;
import org.bukkit.entity.Player;

public class EffectManager implements Runnable {

    @Override
    public void run() {
        update();
    }

    private void update() {
        for (Trail trail : Trail.trails.values()) {
            Player player = trail.getPlayer();

            if (player == null ||
                    !player.hasPermission(trail.getPermission()) ||
                    !player.isOnline() ||
                    player.getWorld().getName().equalsIgnoreCase("bendarenas")) {
                Trail.removeInstance(player);
                continue;
            }

            // Attempts to prevent a trail from being locked in a location when a player dies.
            if (player.isDead()) continue;

            try {
                trail.run();
            } catch (Exception e) {
                HalloweenEffects.logger.warning("Failed to run the trail " + trail.getName() + " for " + trail.getPlayer().getName() + ".");
                HalloweenEffects.logger.info("Disabling defective trail...");

                // Attempts to disable a trail when it can't be ran to prevent console spam
                try {
                    Trail.removeInstance(trail.getPlayer());
                } catch (Exception ex) {
                    HalloweenEffects.logger.warning("Failed to disable the defective trail.");
                    ex.printStackTrace();
                }

                e.printStackTrace();
                HalloweenEffects.logger.info("Defective trail successfully removed.");
            }
        }
    }
}
