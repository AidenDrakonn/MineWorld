package me.drakonn.inferno.mineworld.command.subcommands;

import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.worldmanager.MineWorldManager;
import org.bukkit.command.CommandSender;

public class StartWorld {

    public void run(CommandSender sender){
        if(!sender.hasPermission("mineworld.start")){
            sender.sendMessage(MessageManager.NO_PERMISSION);
            return;
        }

        MineWorldManager.startWorld();
    }
}
