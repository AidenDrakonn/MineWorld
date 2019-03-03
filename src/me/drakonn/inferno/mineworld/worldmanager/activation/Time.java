package me.drakonn.inferno.mineworld.worldmanager.activation;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import me.drakonn.inferno.mineworld.worldmanager.MineWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalTime;

public class Time {

    public void start(){
        new BukkitRunnable() {
            @Override
            public void run() {
            int currentMinute = LocalTime.now().getMinute();
            int currentHour = LocalTime.now().getHour();

            for(String time : SettingsManager.ACTIVATE_AT){
                int hour = Integer.valueOf(time.split(":")[0]);
                int minute = Integer.valueOf(time.split(":")[1]);

                if(add(currentHour, currentMinute)-30 == add(hour, minute)-30)
                    Bukkit.broadcastMessage(MessageManager.WORLD_STARTING_IN_30);
                if(add(currentHour, currentMinute)-10 == add(hour, minute)-10)
                    Bukkit.broadcastMessage(MessageManager.WORLD_STARTING_IN_10);
                if(add(currentHour, currentMinute)-5 == add(hour, minute)-5)
                    Bukkit.broadcastMessage(MessageManager.WORLD_STARTING_IN_5);

                if(currentHour == hour && currentMinute == minute){
                    MineWorldManager.startWorld();
                    return;
                }
            }
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 60*20, 60*20);
    }

    private int add(int currentHour, int currentMinute){
        return Integer.valueOf(Integer.toString(currentHour)+Integer.toString(currentMinute));
    }
}
