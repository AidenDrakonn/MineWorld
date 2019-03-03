package me.drakonn.inferno.mineworld.command.subcommands;

import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import me.drakonn.inferno.mineworld.worldmanager.MineWorldManager;
import me.drakonn.inferno.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveWorld {

    public void run(CommandSender sender, String[] args)
    {
        if(args.length == 1)
        {
            Player player = (Player)sender;
            if(!player.hasPermission("mineworld.leave")) {
                player.sendMessage(MessageManager.NO_PERMISSION);
                return;
            }

            if(!player.getWorld().equals(SettingsManager.WORLD)) {
                player.sendMessage(MessageManager.NOT_IN_WORLD);
            }

            MineWorldManager.leaveWorld(player);
        }

        if(args.length == 2)
        {
            if(!sender.hasPermission("mineworld.leave.others")) {
                sender.sendMessage(MessageManager.NO_PERMISSION);
                return;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if(target == null) {
                sender.sendMessage(Util.color("&c&l(!) &c " + args[1] + " is not online"));
                return;
            }

            if(!target.getWorld().equals(SettingsManager.WORLD)) {
                target.sendMessage(MessageManager.NOT_IN_WORLD);
            }

            MineWorldManager.leaveWorld(target);
        }
    }
}
