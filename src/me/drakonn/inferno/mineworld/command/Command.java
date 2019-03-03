package me.drakonn.inferno.mineworld.command;

import me.drakonn.inferno.mineworld.command.subcommands.*;
import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.utils.Util;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    private EnterWorld enterWorld = new EnterWorld();
    private LeaveWorld leaveWorld = new LeaveWorld();
    private SetKit setKit = new SetKit();
    private SetWorld setWorld = new SetWorld();
    private EditLoot editLoot = new EditLoot();
    private AddItem addItem = new AddItem();
    private StartWorld startWorld = new StartWorld();
    private StopWorld stopWorld = new StopWorld();

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("mineworld"))
            return false;

        if(args.length == 2)
        {
            if(args[0].equalsIgnoreCase("setworld"))
            {
                setWorld.run(commandSender, args);
                return true;
            }

            if(args[0].equalsIgnoreCase("go") || args[0].equalsIgnoreCase("enter"))
            {
                enterWorld.run(commandSender, args);
                return true;
            }

            if(args[0].equalsIgnoreCase("leave") || args[0].equalsIgnoreCase("exit"))
            {
                leaveWorld.run(commandSender, args);
                return true;
            }
        }

        if(args.length == 1)
        {
            if(args[0].equalsIgnoreCase("start")){
                startWorld.run(commandSender);
                return true;
            }

            if(args[0].equalsIgnoreCase("stop")){
                stopWorld.run(commandSender);
                return true;
            }
        }


        if(!(commandSender instanceof Player)) {
            return true;
        }

        Player player = (Player)commandSender;

        if(args.length >= 3 && args[0].equalsIgnoreCase("loot"))
        {
            if(args[1].equalsIgnoreCase("edit"))
            {
                editLoot.run(player, args);
                return true;
            }

            if(args[1].equalsIgnoreCase("additem"))
            {
                addItem.run(player, args);
                return true;
            }

            player.sendMessage(Util.color("&c&l(!) &cInvalid format try /mineworld loot (edit/create/remove) (name)"));
            return false;
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("go") || args[0].equalsIgnoreCase("enter"))
            {
                enterWorld.run(player, args);
                return true;
            }

            if(args[0].equalsIgnoreCase("leave") || args[0].equalsIgnoreCase("exit"))
            {
                leaveWorld.run(player, args);
                return true;
            }
        }


        if(args.length == 1)
        {
            if(args[0].equalsIgnoreCase("setkit"))
            {
                setKit.run(player, args);
                return true;
            }

            if(args[0].equalsIgnoreCase("setworld"))
            {
                setWorld.run(player, args);
                return true;
            }
        }

        for(String string : MessageManager.help)
            player.sendMessage(string);
        return true;
    }
}
