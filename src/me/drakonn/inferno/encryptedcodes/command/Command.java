package me.drakonn.inferno.encryptedcodes.command;

import me.drakonn.inferno.encryptedcodes.datamanagers.MessageManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command implements CommandExecutor {

    private GiveCommand giveCommand = new GiveCommand();

    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        if((args.length == 4 || args.length == 3) && args[0].equalsIgnoreCase("give")){
            giveCommand.run(commandSender, args);
            return true;
        }

        for(String string : MessageManager.help)
            commandSender.sendMessage(string);
        return true;
    }
}
