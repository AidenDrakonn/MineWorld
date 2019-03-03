package me.drakonn.inferno.mineworld.command.subcommands;

import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.loot.Loot;
import me.drakonn.inferno.mineworld.loot.LootManager;
import me.drakonn.inferno.utils.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AddItem {

    public void run(Player player, String[] args) {
        if(!player.hasPermission("mineworld.loot.additem")) {
            player.sendMessage(MessageManager.NO_PERMISSION);
            return;
        }

        if(args.length != 4) {
            player.sendMessage(Util.color("&c&l(!) &cInvalid command, try /mineworld loot additem (name)"));
            return;
        }

        Loot loot = LootManager.getLoot(args[2]);
        if(loot == null) {
            player.sendMessage(Util.color("&c&l(!) &cCan not find any loot with that name"));
            return;
        }

        ItemStack item = player.getItemInHand();

        if(item == null || item.getType().equals(Material.AIR)){
            player.sendMessage(Util.color("&c&l(!) &cYou not holding an item"));
            return;
        }

        if (!Util.isInt(args[3]) || Integer.valueOf(args[3]) > 100 || Integer.valueOf(args[3]) < 0) {
            player.sendMessage(Util.color("&c&l(!) &cChance must be a whole number between 1-100"));
            return;
        }

        int chance = Integer.valueOf(args[3]);
        loot.contents.put(item, chance);
        player.sendMessage(Util.color("&8Item has been added"));
    }
}
