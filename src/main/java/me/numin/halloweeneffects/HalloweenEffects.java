package me.numin.halloweeneffects;

import me.numin.halloweeneffects.commands.CommandRegistry;
import me.numin.halloweeneffects.effects.EffectManager;
import me.numin.halloweeneffects.effects.Trail;
import me.numin.halloweeneffects.effects.trails.PumpkinHead;
import me.numin.halloweeneffects.listeners.InventoryListener;
import me.numin.halloweeneffects.listeners.LeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class HalloweenEffects extends JavaPlugin {

    private HalloweenEffects plugin;
    public static Logger logger;

    @Override
    public void onEnable() {
        plugin = this;
        logger = plugin.getLogger();

        getCommand("he").setExecutor(new CommandRegistry());
        getCommand("halloweeneffects").setExecutor(new CommandRegistry());

        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new LeaveListener(), plugin);

        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this, new EffectManager(), 20, 1);

        logger.info("Successfully enabled " + plugin.getDescription().getName() + ".");
    }

    @Override
    public void onDisable() {
        // Attempts to terminate any active effects
        PumpkinHead.strikeCooldowns.clear();
        for (Player player : Bukkit.getOnlinePlayers())
            Trail.removeInstance(player);

        logger.info("Successfully disabled " + plugin.getDescription().getName() + ".");
    }
}
