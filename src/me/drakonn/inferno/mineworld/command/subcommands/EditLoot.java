package me.drakonn.inferno.mineworld.command.subcommands;

import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.loot.Loot;
import me.drakonn.inferno.mineworld.loot.LootManager;
import me.drakonn.inferno.utils.Util;
import org.bukkit.entity.Player;

public class EditLoot
{

    public void run(Player player, String[] args)
    {
        if(!player.hasPermission("mineworld.loot.edit")) {
            player.sendMessage(MessageManager.NO_PERMISSION);
            return;
        }

        if(args.length != 4 && args.length != 3) {
            player.sendMessage(Util.color("&c&l(!) &cInvalid command, try /mineworld loot edit (name) [chance]"));
            return;
        }

        Loot loot = LootManager.getLoot(args[2]);
        if(loot == null) {
            player.sendMessage(Util.color("&c&l(!) &cCan not find any loot with that name"));
            return;
        }

        player.openInventory(loot.getContents());
    }
}

