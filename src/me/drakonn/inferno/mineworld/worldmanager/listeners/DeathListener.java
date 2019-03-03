package me.drakonn.inferno.mineworld.worldmanager.listeners;

import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import me.drakonn.inferno.mineworld.kit.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player))
            return;

        Player player = (Player)event.getEntity();

        if(!player.getWorld().equals(SettingsManager.WORLD))
            return;

        if(player.getHealth() - event.getDamage() > 0)
            return;

        Kit.removeKit(player);
    }
}
