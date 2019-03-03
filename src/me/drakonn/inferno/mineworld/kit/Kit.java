package me.drakonn.inferno.mineworld.kit;

import me.drakonn.inferno.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class Kit {

    private static ItemStack helmet = null;
    private static ItemStack chestplate = null;
    private static ItemStack leggings = null;
    private static ItemStack boots = null;
    private static List<ItemStack> items = new ArrayList<>();

    private Main main;
    public Kit (Main main) {
        this.main = main;
    }

    public static void setKit(PlayerInventory inv) {
        items.clear();
        helmet = inv.getHelmet();
        chestplate = inv.getChestplate();
        leggings = inv.getLeggings();
        boots = inv.getBoots();

        for(ItemStack item : inv.getContents())
            items.add(item);
    }

    public static void removeKit(Player player){
        for(ItemStack item : items){
            player.getInventory().remove(item);
        }

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public static void applyKit(Player player) {
        player.getInventory().setLeggings(leggings);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setHelmet(helmet);
        player.getInventory().setBoots(boots);
        if(helmet != null)
            helmet.setAmount(1);
        if(chestplate != null)
            chestplate.setAmount(1);
        if(leggings != null)
            leggings.setAmount(1);
        if(boots != null)
            boots.setAmount(1);
        int i = 0;
        for(ItemStack item : items) {
            player.getInventory().setItem(i, item);
            i++;
        }
    }

    public void saveKit()
    {
        System.out.println("[MineWorld] Saving kit to config");
        if(boots != null)
            main.getConfig().set("mineworld.kit.boots", boots);

        if(leggings != null)
            main.getConfig().set("mineworld.kit.leggings", leggings);

        if(chestplate != null)
            main.getConfig().set("mineworld.kit.chestplate", chestplate);

        if(helmet != null)
            main.getConfig().set("mineworld.kit.helmet", helmet);

        int i = 0;
        for(ItemStack item : items)
            main.getConfig().set("mineworld.kit."+Integer.toString(i++), item);
    }

    public void loadKit()
    {
        if(!main.getConfig().isConfigurationSection("mineworld.kit"))
            return;

        ConfigurationSection section = main.getConfig().getConfigurationSection("mineworld.kit");
        for(String key : section.getKeys(false))
        {
            if(key.equalsIgnoreCase("helmet")) {
                helmet = section.getItemStack(key);
                continue;
            }

            if(key.equalsIgnoreCase("chestplate")) {
                chestplate = section.getItemStack(key);
                continue;
            }

            if(key.equalsIgnoreCase("leggings")) {
                leggings = section.getItemStack(key);
                continue;
            }

            if(key.equalsIgnoreCase("boots")) {
                boots = section.getItemStack(key);
                continue;
            }

            items.add(section.getItemStack(key));
        }
    }
}
