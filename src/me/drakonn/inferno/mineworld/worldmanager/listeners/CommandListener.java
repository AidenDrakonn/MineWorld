package me.drakonn.inferno.mineworld.worldmanager.listeners;

import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        if(!player.getWorld().equals(SettingsManager.WORLD))
            return;

        for(String string : event.getMessage().split(" ")){
            if(SettingsManager.BLOCKED_COMMANDS.contains(string)){
                player.sendMessage(MessageManager.COMMAND_BLOCKED);
                event.setCancelled(true);
                return;
            }
        }
    }
}
