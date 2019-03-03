package me.drakonn.inferno.mineworld.loot;

import me.drakonn.inferno.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class LootManager {

    private static List<Loot> lootList = new ArrayList<>();
    private static Loot common;
    private static Loot rare;
    private static Loot legendary;


    private Main main;
    public LootManager(Main main)
    {
        this.main = main;
    }

    public void loadLoot() {
        for (String name : Arrays.asList("common", "rare", "legendary"))
        {
            int chance = main.getConfig().getInt("mineworld.chance."+name);
            HashMap<ItemStack, Integer> contents = new HashMap<>();
            if(main.getConfig().contains("mineworld.loot."+name+".contents")) {
                for (String key : main.getConfig().getConfigurationSection("mineworld.loot."+name+".contents").getKeys(false)) {
                    ConfigurationSection section = main.getConfig().getConfigurationSection("mineworld.loot." + name + ".contents." + key);
                    int itemChance = section.getInt("chance");
                    ItemStack item = section.getItemStack("item");
                    contents.put(item, itemChance);
                }
            }
            Loot loot = new Loot(name, chance, contents);
            lootList.add(loot);
            if(name.equals("common"))
                common = loot;
            if(name.equals("rare"))
                rare = loot;
            if(name.equals("legendary"))
                legendary = loot;
        }
    }

    public void saveLoot() {
        System.out.println("[MineWorld] Saving loot to config");
        main.getConfig().set("mineworld.loot", null);
        for(Loot loot : lootList) {
            String name = loot.getName();
            System.out.println("[MineWorld] Saving loot " + name + " to config");
            int i = 0;
            for(ItemStack item : loot.contents.keySet()) {
                i++;
                main.getConfig().set("mineworld.loot."+name+".contents."+Integer.toString(i)+".item", item);
                main.getConfig().set("mineworld.loot."+name+".contents."+Integer.toString(i)+".chance", loot.contents.get(item));
            }
        }
        main.saveConfig();
    }

    public static Loot getLoot(String name) {
        return lootList.stream().filter(loot -> loot.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static Loot getRandomLoot(){
        int random = (int)(long)Math.round(Math.random()*100);
        System.out.println("random: "+random);

        int chance = common.getChance();
        System.out.println("common: "+chance);
        if(chance > random)
            return common;
        chance = chance+rare.getChance();
        System.out.println("rare: "+chance);
        if(chance > random)
            return rare;
        chance = chance+legendary.getChance();
        System.out.println("legendary: "+chance);
        if(chance > random)
            return legendary;

        return null;
    }
}
