package me.drakonn.inferno.mineworld.worldmanager.activation;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import me.drakonn.inferno.mineworld.worldmanager.MineWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerCount {


    public void start(){
        new BukkitRunnable() {
            @Override
            public void run() {
            int players = Bukkit.getServer().getOnlinePlayers().size();
            if(players < SettingsManager.ACTIVATE_AT_PLAYERS)
                return;

            if(MineWorldManager.isEnabled())
                return;

            MineWorldManager.startWorld();
            return;
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 60*20, 60*20);
    }
}
