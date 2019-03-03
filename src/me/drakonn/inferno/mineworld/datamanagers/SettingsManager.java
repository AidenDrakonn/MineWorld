package me.drakonn.inferno.mineworld.datamanagers;

import me.drakonn.inferno.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SettingsManager {

    public static World WORLD;
    public static World EXIT_WORLD;
    public static int RANGE;
    public static int ACTIVATE_AT_PLAYERS;
    public static int LAST_FOR;
    public static int CHESTS;
    public static int REGEN_DELAY;
    public static int MIN_BETWEEN;
    public static boolean REGEN_ALL;
    public static boolean ALLOW_BUILDING;
    public static List<Material> REGEN_BLOCKS = new ArrayList<>();
    public static List<String> BLOCKED_COMMANDS = new ArrayList<>();
    public static List<String> ACTIVATE_AT = new ArrayList<>();


    private Main main;
    public SettingsManager(Main main)
    {
        this.main = main;
    }

    public void loadSettings()
    {
        REGEN_BLOCKS.clear();
        BLOCKED_COMMANDS.clear();
        ConfigurationSection section = main.getConfig().getConfigurationSection("mineworld");
        WORLD = Bukkit.getWorld(section.getString("world"));
        EXIT_WORLD = Bukkit.getWorld(section.getString("exitworld"));
        RANGE = section.getInt("range");
        ACTIVATE_AT_PLAYERS = section.getInt("activate.atplayers");
        LAST_FOR = section.getInt("lastfor");
        CHESTS = section.getInt("chests");
        REGEN_DELAY = section.getInt("regendelay");
        MIN_BETWEEN = section.getInt("minbetween");
        REGEN_ALL = section.getBoolean("regenall");
        ALLOW_BUILDING = section.getBoolean("allowbuilding");
        BLOCKED_COMMANDS.addAll(section.getStringList("blockedcommands"));
        ACTIVATE_AT.addAll(section.getStringList("attime"));
        for(String name : section.getStringList("regenblocks")) {
            Material material = Material.getMaterial(name);
            if(material != null)
                REGEN_BLOCKS.add(material);
            else System.out.println("[MineWorld] Material " + name + " is invalid");
        }

        new BukkitRunnable(){
            @Override
            public void run() {
                WORLD = Bukkit.getWorld(section.getString("world"));
            }
        }.runTaskLater(Main.getInstance(), 5*20);

    }
}
