package me.numin.halloweeneffects.listeners;

import me.numin.halloweeneffects.MessageHandler;
import me.numin.halloweeneffects.effects.Trail;
import me.numin.halloweeneffects.effects.trails.PumpkinHead;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        if (!Trail.trails.containsKey(player)) return;
        Trail trail = Trail.trails.get(player);

        if (!(trail instanceof PumpkinHead)) return;

        if (event.getSlotType() == InventoryType.SlotType.ARMOR &&
                event.getCurrentItem().equals(player.getInventory().getHelmet()) &&
                player.getInventory().getHelmet() != null) {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage(MessageHandler.pumpkinThief());
        }
    }
}
