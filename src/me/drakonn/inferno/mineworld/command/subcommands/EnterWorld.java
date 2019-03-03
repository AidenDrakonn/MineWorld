package me.drakonn.inferno.mineworld.command.subcommands;

import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.worldmanager.MineWorldManager;
import me.drakonn.inferno.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnterWorld {

    public void run(CommandSender sender, String[] args)
    {
        if(!MineWorldManager.isEnabled()) {
            sender.sendMessage(MessageManager.WORLD_NOT_OPEN);
            return;
        }

        if(args.length == 1)
        {
            Player player = (Player)sender;
            if(!player.hasPermission("mineworld.enter")) {
                player.sendMessage(MessageManager.NO_PERMISSION);
                return;
            }

            if(!Util.isEmpty(player.getInventory())) {
                player.sendMessage(MessageManager.NO_ITEMS);
                return;
            }

            MineWorldManager.enterWorld(player);
        }

        if(args.length == 2)
        {
            if(!sender.hasPermission("mineworld.enter.others")) {
                sender.sendMessage(MessageManager.NO_PERMISSION);
                return;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if(target == null) {
                sender.sendMessage(Util.color("&c&l(!) &c " + args[1] + " is not online"));
                return;
            }

            if(!Util.isEmpty(target.getInventory())) {
                target.sendMessage(MessageManager.NO_ITEMS);
                return;
            }

            MineWorldManager.enterWorld(target);
        }
    }
}
