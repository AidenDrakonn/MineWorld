package me.drakonn.inferno.mineworld.worldmanager.listeners;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        if(!block.getWorld().equals(SettingsManager.WORLD))
            return;

        if(!SettingsManager.REGEN_ALL && !SettingsManager.REGEN_BLOCKS.contains(block.getType()))
            return;

        Material type = block.getType();
        byte durability = block.getData();

        new BukkitRunnable(){
            @Override
            public void run() {
            block.setType(type);
            block.setData(durability);
            }
        }.runTaskLater(Main.getInstance(), SettingsManager.REGEN_DELAY*20);
    }
}
