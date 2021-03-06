package me.numin.halloweeneffects;

import me.numin.halloweeneffects.commands.CommandRegistry;
import me.numin.halloweeneffects.effects.EffectManager;
import me.numin.halloweeneffects.effects.Effect;
import me.numin.halloweeneffects.effects.effects.PumpkinHead;
import me.numin.halloweeneffects.listeners.InventoryListener;
import me.numin.halloweeneffects.listeners.LeaveListener;
import me.numin.halloweeneffects.listeners.SwingListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class HalloweenEffects extends JavaPlugin {

    public static HalloweenEffects plugin;
    public static Logger logger;

    @Override
    public void onEnable() {
        plugin = this;
        logger = plugin.getLogger();

        getCommand("he").setExecutor(new CommandRegistry());
        getCommand("halloweeneffects").setExecutor(new CommandRegistry());

        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new LeaveListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SwingListener(), plugin);

        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this, new EffectManager(), 20, 1);

        logger.info("Successfully enabled " + plugin.getDescription().getName() + ".");
    }

    @Override
    public void onDisable() {
        // Attempts to terminate any active effects
        PumpkinHead.strikeCooldowns.clear();
        for (Player player : Bukkit.getOnlinePlayers())
            Effect.removeInstance(player);

        logger.info("Successfully disabled " + plugin.getDescription().getName() + ".");
    }

    public static HalloweenEffects getInstance() {
        return plugin;
    }
}
