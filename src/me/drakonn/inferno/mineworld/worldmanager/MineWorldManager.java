package me.drakonn.inferno.mineworld.worldmanager;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import me.drakonn.inferno.mineworld.kit.Kit;
import me.drakonn.inferno.mineworld.loot.Loot;
import me.drakonn.inferno.mineworld.loot.LootManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineWorldManager {

    public static long lastActivated;
    private static boolean enabled = false;
    public static List<Location> chests = new ArrayList<>();

    private Main main;
    public MineWorldManager(Main main){
        this.main = main;
    }

    public void intialise(){
        if(main.getConfig().contains("mineworld.lastactivated"))
            lastActivated = main.getConfig().getLong("mineworld.lastactivated");
        else
            lastActivated = System.currentTimeMillis() - SettingsManager.MIN_BETWEEN*1000;
    }

    public static void leaveWorld(Player player){
        player.sendMessage(MessageManager.LEFT_WORLD);
        if(SettingsManager.EXIT_WORLD == null)
            player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
        else
            player.teleport(SettingsManager.EXIT_WORLD.getSpawnLocation());
        Kit.removeKit(player);
    }

    public static void enterWorld(Player player){
        player.sendMessage(MessageManager.ENTERED_WORLD);
        player.teleport(SettingsManager.WORLD.getSpawnLocation());
        Kit.applyKit(player);
    }

    public static void startWorld(){
        if(enabled)
            endWorld();
        if(MineWorldManager.lastActivated + SettingsManager.MIN_BETWEEN*1000 > System.currentTimeMillis())
            return;

        Bukkit.broadcastMessage(MessageManager.WORLD_OPEN);

        enabled = true;
        spawnLoot();

        if(SettingsManager.LAST_FOR - 600 > 0) {
            new BukkitRunnable() {
                public void run() {
                    Bukkit.broadcastMessage(MessageManager.ENDING_IN_10);
                }
            }.runTaskLater(Main.getInstance(), SettingsManager.LAST_FOR * 20 - 600);
        }

        if(SettingsManager.LAST_FOR - 300 > 0) {
            new BukkitRunnable() {
                public void run() {
                    Bukkit.broadcastMessage(MessageManager.ENDING_IN_5);
                }
            }.runTaskLater(Main.getInstance(), SettingsManager.LAST_FOR * 20 - 300);
        }

        if(SettingsManager.LAST_FOR - 60 > 0) {
            new BukkitRunnable() {
                public void run() {
                    Bukkit.broadcastMessage(MessageManager.ENDING_IN_1);
                }
            }.runTaskLater(Main.getInstance(), SettingsManager.LAST_FOR * 20 - 60);
        }

        new BukkitRunnable(){
            public void run() {
            endWorld();
            }
        }.runTaskLater(Main.getInstance(), SettingsManager.LAST_FOR*20);
    }

    public static void endWorld(){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.getWorld().equals(SettingsManager.WORLD))
                leaveWorld(player);
        }

        for(Location location : chests){
            if(location.getBlock().getState() instanceof Chest) {
                Chest chest = (Chest) location.getBlock().getState();
                chest.getBlockInventory().clear();
                location.getBlock().setType(Material.AIR);
            }
        }
        chests.clear();
        enabled = false;
        Bukkit.broadcastMessage(MessageManager.WORLD_CLOSED);
    }

    public static void spawnLoot(){
        for(int i = 0; i < SettingsManager.CHESTS; i++){
            Location location = getRandomLocation();
            location.getBlock().setType(Material.CHEST);
            Chest chest = (Chest)location.getBlock().getState();
            Loot loot = LootManager.getRandomLoot();
            if(loot != null) {
                chest.getInventory().setContents(loot.getInventory().getContents());
                chests.add(location);
            } else {
                System.out.println("[MineWorld] Loot is configured wrong, total % does not add to 100");
                location.getBlock().setType(Material.AIR);
            }
        }
    }

    private static Location getRandomLocation() {
        Random rand = new Random();
        int range = SettingsManager.RANGE;
        World world = SettingsManager.WORLD;
        int locX = rand.nextInt((range - (-range)) + 1) + (-range);
        int locZ = rand.nextInt((range - (-range)) + 1) + (-range);
        int y = world.getHighestBlockYAt(locX, locZ);
        Location loc = new Location(world, locX, y, locZ);
        if(!loc.getBlock().getType().equals(Material.AIR))
            loc.setY(y+1);
        return loc;
    }

    public void save(){
        main.getConfig().set("mineworld.lastactivated", lastActivated);
    }

    public static boolean isEnabled() {
        return enabled;
    }
}
