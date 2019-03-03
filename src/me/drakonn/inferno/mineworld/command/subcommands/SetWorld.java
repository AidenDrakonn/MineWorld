package me.drakonn.inferno.mineworld.command.subcommands;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import me.drakonn.inferno.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWorld {

    public void run(CommandSender sender, String[] args)
    {
        if(!sender.hasPermission("mineworld.setworld")){
            sender.sendMessage(MessageManager.NO_PERMISSION);
            return;
        }
        if(args.length == 1) {
            Player player = (Player)sender;
            World world = player.getWorld();
            SettingsManager.WORLD = world;
            Main.getInstance().getConfig().set("mineworld.world", world.getName());
            Main.getInstance().saveConfig();
            Main.getInstance().reloadConfig();
        }

        if(args.length == 2) {
            World world = Bukkit.getWorld(args[1]);
            if(world == null){
                sender.sendMessage(Util.color("&c&l(!) &cThat world does not exist"));
                return;
            }
            SettingsManager.WORLD = world;
            Main.getInstance().getConfig().set("mineworld.world", world.getName());
            Main.getInstance().saveConfig();
            Main.getInstance().reloadConfig();
        }
    }
}
