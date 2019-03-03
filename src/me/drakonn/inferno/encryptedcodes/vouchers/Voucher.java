package me.drakonn.inferno.encryptedcodes.vouchers;

import me.drakonn.inferno.Main;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;

public class Voucher {

    private List<String> atStart;
    private List<String> atEnd;
    private int lastFor;
    private String name;
    private ItemStack item;
    public HashMap<String, Long> data = new HashMap<>();

    public Voucher(String name, ItemStack item, int lastFor,List<String> atStart, List<String> atEnd){
        this.name = name;
        this.lastFor = lastFor;
        this.atStart = atStart;
        this.atEnd = atEnd;
        this.item = item;
    }

    public void run(String username){
        for(String command : atStart){
            command = command.replaceAll("%player%", username);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        }
        data.put(username, System.currentTimeMillis());

        new BukkitRunnable(){
            @Override
            public void run() {
                data.remove(username);
                for(String command : atEnd){
                    command = command.replaceAll("%player%", username);
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
        }.runTaskLater(Main.getInstance(),lastFor*20);
    }

    public void resume(String username, int lastFor){
        new BukkitRunnable(){
            @Override
            public void run() {
                for(String command : atEnd){
                    data.remove(username);
                    command = command.replaceAll("%player%", username);
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
        }.runTaskLater(Main.getInstance(),lastFor*20);


    }

    public String getName() {
        return name;
    }

    public ItemStack getItem() {
        return item.clone();
    }
}
