package me.numin.halloweeneffects.effects.effects;

import com.projectkorra.projectkorra.GeneralMethods;
import me.numin.halloweeneffects.effects.Effect;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class ScaredWet extends Effect {

    public static HashMap<String, Long> blastCooldowns = new HashMap<>();

    private Location blastLoc;
    private Location origin;
    private Vector direction;
    public boolean canShoot;
    public boolean initializeVars;
    public long cooldown = 30;

    public ScaredWet(Player player) {
        super(player);

        canShoot = false;
    }

    @Override
    public void run() {
        Location location = player.getLocation();
        player.getWorld().spawnParticle(Particle.DRIP_WATER, location, 1, 0.3, 0.5, 0.3);

        if (canShoot) {
            shoot();
        }
    }

    public void shoot() {
        if (initializeVars) {
            origin = player.getLocation().add(0, 1, 0).clone();
            blastLoc = origin.clone();
            direction = origin.getDirection();
            initializeVars = false;
        }
        blastLoc.add(direction.multiply(1));
        blastLoc.getWorld().spawnParticle(Particle.FLAME, blastLoc, 1, 0, 0, 0, 0.05);
        blastLoc.getWorld().spawnParticle(Particle.BLOCK_CRACK, blastLoc, 10, 0, 0, 0, Material.OBSIDIAN.createBlockData());
        blastLoc.getWorld().playSound(blastLoc, Sound.ENTITY_BAT_AMBIENT, 0.05F, 1);

        if (origin.distance(blastLoc) > 15) canShoot = false;

        if (GeneralMethods.isSolid(blastLoc.getBlock())) {
            blastLoc.getWorld().spawnParticle(Particle.LAVA, blastLoc, 5, 1, 3, 1);
            blastLoc.getWorld().playSound(blastLoc, Sound.ENTITY_WITCH_AMBIENT, 1, 1);
            canShoot = false;
        }

        for (Player target : GeneralMethods.getPlayersAroundPoint(blastLoc, 0.75)) {
            if (target.getUniqueId() != player.getUniqueId()) {
                Location targetLoc = target.getLocation().add(0, 1, 0);
                targetLoc.getWorld().spawnParticle(Particle.SPELL_WITCH, targetLoc, 5, 0.3, 0.4, 0.3);
                targetLoc.getWorld().spawnParticle(Particle.WATER_SPLASH, targetLoc, 100, 0.2, 0.5, 0.2);
                target.getLocation().getWorld().playSound(target.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.5F, 1.5F);
                target.getLocation().getWorld().playSound(target.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 2, 1);
                target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 1), true);
                target.sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "Jeepers Creepers!", ChatColor.BLACK + "I'm excreting moisture...");
                canShoot = false;
            }
        }
    }

    public boolean isOnCooldown() {
        HashMap<String, Long> cooldowns = blastCooldowns;

        if (cooldowns.containsKey(player.getName())) {
            long timeLeft = (((cooldowns.get(player.getName()) / 1000) + cooldown) - (System.currentTimeMillis() / 1000));
            if (timeLeft <= 0) {
                cooldowns.remove(player.getName());
                return false;
            } else {
                return true;
            }
        } else {
            cooldowns.put(player.getName(), System.currentTimeMillis());
            return false;
        }
    }

    @Override
    public String getName() {
        return "ScaredWet";
    }

    @Override
    public String getPermission() {
        return baseNode + getName().toLowerCase();
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
