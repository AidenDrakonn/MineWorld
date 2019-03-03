package me.drakonn.inferno.mineworld.loot;

import me.drakonn.inferno.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Loot {

    public HashMap<ItemStack, Integer> contents;
    private int chance;
    private String name;
    public ItemStack fill;

    public Loot(String name, int chance, HashMap<ItemStack, Integer> contents) {
        this.name = name;
        this.chance = chance;
        this.contents = contents;
        fill = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta meta = fill.getItemMeta();
        meta.setDisplayName("");
        fill.setItemMeta(meta);
        fill.setDurability(Short.valueOf("14"));
    }

    public int getChance() {
        return chance;
    }

    public String getName() {
        return name;
    }

    public Inventory getContents(){
        Inventory inv = Bukkit.createInventory(null, 27, name);
        for(ItemStack item : contents.keySet())
            inv.addItem(item);


        for(int i = 0; i < 27; i++){
            if(inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR))
                inv.setItem(i, fill);
        }
        return inv;
    }

    public Inventory getInventory(){
        Inventory inv = Bukkit.createInventory(null, 27, name);
        for(ItemStack item : contents.keySet()){
            int random = (int)(long)Math.round(Math.random()*100);
            int chance = contents.get(item);
            if(chance > random)
                inv.addItem(item);
        }
        return inv;
    }
}
