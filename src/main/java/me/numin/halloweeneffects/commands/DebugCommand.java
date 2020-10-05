package me.numin.halloweeneffects.commands;

import me.numin.halloweeneffects.HalloweenEffects;
import me.numin.halloweeneffects.effects.Effect;
import me.numin.halloweeneffects.effects.EffectManager;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class DebugCommand {

    public DebugCommand(CommandSender sender) {
        int activeEffects = Effect.effects.size();

        List<World> worlds = new ArrayList<>();
        for (Effect effect : Effect.effects.values()) {
            World world = effect.getPlayer().getWorld();
            if (!worlds.contains(world))
                worlds.add(world);
        }

        sender.sendMessage("-------- Halloween Effects Debug --------");
        sender.sendMessage("Author: Numin");
        sender.sendMessage("Plugin version: " + HalloweenEffects.plugin.getDescription().getVersion());
        sender.sendMessage("API version:");
        sender.sendMessage("- Spigot: "+ HalloweenEffects.plugin.getDescription().getAPIVersion());
        sender.sendMessage("- ProjectKorra: 1.8.9.43");
        sender.sendMessage("");

        sender.sendMessage("Active player effects: " + activeEffects);
        sender.sendMessage("Active worlds:");
        for (World world : worlds) {
            sender.sendMessage("- " + world.getName());
        }
        sender.sendMessage("");

        sender.sendMessage("Disabled worlds:");
        for (String disabledWorld : EffectManager.disabledWorlds) {
            sender.sendMessage("- " + disabledWorld);
        }
        sender.sendMessage("");

        sender.sendMessage("Permissions:");
        for (Permission permission : HalloweenEffects.plugin.getDescription().getPermissions()) {
            sender.sendMessage("- " + permission.getName() + ": " + permission.getDescription());
        }
        sender.sendMessage("-------- End of Debug --------");
    }
}
