package me.drakonn.inferno.mineworld.worldmanager.listeners;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import me.drakonn.inferno.mineworld.loot.Loot;
import me.drakonn.inferno.mineworld.loot.LootManager;
import me.drakonn.inferno.mineworld.worldmanager.MineWorldManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class LootCloseListener implements Listener {

    private HashMap<UUID, Location> data = new HashMap<>();

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(!player.getWorld().equals(SettingsManager.WORLD))
            return;

        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;

        UUID uuid = player.getUniqueId();
        Location location = event.getClickedBlock().getLocation();

        if(event.getClickedBlock().getState() instanceof Chest){
            data.put(uuid, location);
        }

        new BukkitRunnable(){
            @Override
            public void run() {
            if(data.get(uuid).equals(location))
                data.remove(uuid);
            }
        }.runTaskLater(Main.getInstance(), SettingsManager.LAST_FOR*10);
    }

    @EventHandler
    public void onChestClose(InventoryCloseEvent event){
        Player player = (Player)event.getPlayer();
        if(!player.getWorld().equals(SettingsManager.WORLD))
            return;

        Location location = data.get(player.getUniqueId());

        if(location == null)
            return;

        Inventory inv = event.getInventory();
        for(ItemStack item : inv)
        {
            if(item != null && item.getType() != Material.AIR)
                return;
        }

        location.getBlock().setType(Material.AIR);
        MineWorldManager.chests.remove(location);
    }
}
