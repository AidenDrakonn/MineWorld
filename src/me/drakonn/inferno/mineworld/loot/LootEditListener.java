package me.drakonn.inferno.mineworld.loot;

import me.drakonn.inferno.utils.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class LootEditListener implements Listener {

    @EventHandler
    public void onLootInvEdit(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked();
        Loot loot = LootManager.getLoot(event.getInventory().getName());
        if(loot == null)
            return;

        event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        if(item == null || item.getType().equals(Material.AIR))
            return;

        if(item.equals(loot.fill))
            return;
        loot.contents.remove(item);

        player.openInventory(loot.getContents());
        player.sendMessage(Util.color("&8Item removed"));
    }
}
