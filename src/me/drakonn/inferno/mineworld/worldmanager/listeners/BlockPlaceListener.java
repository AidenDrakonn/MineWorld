package me.drakonn.inferno.mineworld.worldmanager.listeners;

import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();

        if(SettingsManager.ALLOW_BUILDING)
            return;

        event.setCancelled(true);
        player.sendMessage(MessageManager.CANT_BUILD);
    }
}
