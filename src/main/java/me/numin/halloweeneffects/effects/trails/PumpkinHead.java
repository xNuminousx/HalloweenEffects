package me.numin.halloweeneffects.effects.trails;

import me.numin.halloweeneffects.effects.Effect;
import me.numin.halloweeneffects.effects.Trail;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class PumpkinHead extends Trail implements Effect {

    public static HashMap<String, Long> strikeCooldowns = new HashMap<>();

    private Particle.DustOptions orange = new Particle.DustOptions(Color.fromRGB(255, 135, 21), 1);
    private ItemStack initialHelmet;

    public PumpkinHead(Player player) {
        super(player);

        initialHelmet = player.getInventory().getHelmet();

        ItemStack pumpkin = new ItemStack(Material.JACK_O_LANTERN);
        ItemMeta pumpkinMeta = pumpkin.getItemMeta();
        pumpkinMeta.setDisplayName("PumpkinHead");
        pumpkin.setItemMeta(pumpkinMeta);

        player.getInventory().setHelmet(pumpkin);

        if (canStrike()) player.getWorld().strikeLightningEffect(player.getLocation());
    }

    @Override
    public void run() {
        Location playerLocation = player.getEyeLocation();
        player.getWorld().spawnParticle(Particle.REDSTONE, playerLocation, 1, 0.3, 0.2, 0.3, 0, orange);
    }

    public void removePumpkinHead() {
        if (initialHelmet == null) player.getInventory().setHelmet(new ItemStack(Material.AIR));
        else player.getInventory().setHelmet(initialHelmet);
    }

    private boolean canStrike() {
        HashMap<String, Long> cooldowns = strikeCooldowns;

        if (cooldowns.containsKey(player.getName())) {
            long timeLeft = (((cooldowns.get(player.getName()) / 1000) + 60) - (System.currentTimeMillis() / 1000));
            if (timeLeft <= 0) {
                cooldowns.remove(player.getName());
                return true;
            } else return false;
        } else {
            cooldowns.put(player.getName(), System.currentTimeMillis());
            return true;
        }
    }

    @Override
    public String getName() {
        return "PumpkinHead";
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
