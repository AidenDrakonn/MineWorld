package me.drakonn.inferno.mineworld.worldmanager.listeners;

import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import me.drakonn.inferno.mineworld.worldmanager.MineWorldManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLogoutListener implements Listener {

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event){
        Player player = event.getPlayer();

        if(!player.getWorld().equals(SettingsManager.WORLD))
            return;

        MineWorldManager.leaveWorld(player);
    }
}
