package me.numin.halloweeneffects.effects.trails;

import me.numin.halloweeneffects.effects.Effect;
import me.numin.halloweeneffects.effects.Trail;
import org.bukkit.*;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SpookyBats extends Trail implements Effect {

    private ArrayList<Bat> livingBats = new ArrayList<>();
    private Particle.DustOptions black = new Particle.DustOptions(Color.fromRGB(0, 0, 0), 1);

    public SpookyBats(Player player) {
        super(player);
    }

    @Override
    public void run() {
        Location location = player.getLocation().add(0, 1, 0);
        player.getWorld().spawnParticle(Particle.TOWN_AURA, location, 2, 0.3, 0.5, 0.3, 0);

        // Bat spawning logic
        if (new Random().nextInt(100) == 1 && !(player.isSprinting() || player.isFlying() || livingBats.size() > 5)) {
            spawnBat();
        }
        trackBats();
    }

    private void killBat(Bat bat) {
        if (livingBats.contains(bat)) livingBats.remove(bat);
        bat.remove();
        player.getWorld().spawnParticle(Particle.FLAME, bat.getLocation().add(0, 0.3, 0), 10, 0, 0, 0, 0.05);
    }

    public void killAllBats() {
        for (Iterator<Bat> iterator = livingBats.iterator(); iterator.hasNext();) {
            Bat bat = iterator.next();
            iterator.remove();
            killBat(bat);
        }
    }

    private void spawnBat() {
        // Only spawn a bat if the player is not underwater or stuck inside of blocks.
        Location playerHead = player.getLocation().add(0, 1.5, 0);
        Location playerTorso = player.getLocation().add(0, 1, 0);
        Location playerFeet = player.getLocation();
        if (playerFeet.getBlock().getType() != Material.AIR || playerHead.getBlock().getType() != Material.AIR) return;

        player.getWorld().spawnParticle(Particle.FLAME, playerTorso, 10, 0, 0, 0, 0.05);
        Bat bat = player.getWorld().spawn(playerTorso, Bat.class);
        livingBats.add(bat);
    }

    private void trackBats() {
        if (livingBats == null || livingBats.isEmpty()) return;

        // Loops through all existing bats. Uses Iterator to fix ConcurrentModificationException (altering collection while looping)
        for (Iterator<Bat> iterator = livingBats.iterator(); iterator.hasNext();) {
            Bat bat = iterator.next();
            Location batLocation = bat.getLocation().add(0, 0.3, 0);

            // Conditionals to kill bats
            if (bat.getTicksLived() > 20 * 5 ||
                    bat.getLocation().distance(player.getLocation()) > 10 ||
                    bat.isDead()) {
                iterator.remove();
                killBat(bat);
            }

            // Particles around bat.
            bat.getWorld().spawnParticle(Particle.REDSTONE, batLocation, 2, 0.2, 0.2, 0.2, 0, black);
        }
    }

    @Override
    public String getName() {
        return "SpookyBats";
    }

    @Override
    public String getPermission() {
        return "halloween.trail.spookybats";
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}