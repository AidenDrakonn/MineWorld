package me.drakonn.inferno.mineworld.command.subcommands;

import me.drakonn.inferno.mineworld.kit.Kit;
import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.utils.Util;
import org.bukkit.entity.Player;

public class SetKit {

    public void run(Player player, String[] args)
    {
        if(!player.hasPermission("mineworld.setkit")) {
            player.sendMessage(MessageManager.NO_PERMISSION);
            return;
        }

        Kit.setKit(player.getInventory());
        player.sendMessage(Util.color("&8iMineWorld kit has been set to your inventory"));
    }
}
