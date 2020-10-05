package me.numin.halloweeneffects.effects;

import me.numin.halloweeneffects.HalloweenEffects;
import org.bukkit.entity.Player;

public class EffectManager implements Runnable {

    public static String[] disabledWorlds = {"bendarenas"};

    @Override
    public void run() {
        update();
    }

    private void update() {
        for (Effect effect : Effect.effects.values()) {
            Player player = effect.getPlayer();

            for (String world : disabledWorlds) {
                if (player.getWorld().getName().equalsIgnoreCase(world))
                    Effect.removeInstance(player);
            }

            if (player == null ||
                    !player.hasPermission(effect.getPermission()) ||
                    !player.isOnline()) {
                Effect.removeInstance(player);
                continue;
            }

            // Attempts to prevent a trail from being locked in a location when a player dies.
            if (player.isDead()) continue;

            try {
                effect.run();
            } catch (Exception e) {
                HalloweenEffects.logger.warning("Failed to run the trail " + effect.getName() + " for " + effect.getPlayer().getName() + ".");
                HalloweenEffects.logger.info("Disabling defective trail...");

                // Attempts to disable a trail when it can't be ran to prevent console spam
                try {
                    Effect.removeInstance(effect.getPlayer());
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
