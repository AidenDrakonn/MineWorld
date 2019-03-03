package me.drakonn.inferno.mineworld.datamanagers;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.utils.Util;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    public static String ENTERED_WORLD;
    public static String LEFT_WORLD;
    public static String WORLD_STARTING_IN_30;
    public static String WORLD_STARTING_IN_10;
    public static String WORLD_STARTING_IN_5;
    public static String ENDING_IN_10;
    public static String ENDING_IN_5;
    public static String ENDING_IN_1;
    public static String WORLD_OPEN;
    public static String WORLD_CLOSED;
    public static String NO_ITEMS;
    public static String INVALID_COMMAND;
    public static String NO_PERMISSION;
    public static String WORLD_NOT_OPEN;
    public static String NOT_IN_WORLD;
    public static String COMMAND_BLOCKED;
    public static String CANT_BUILD;
    public static List<String> help = new ArrayList<>();

    private Main main;
    public MessageManager(Main main)
    {
        this.main = main;
    }

    public void loadMessages()
    {
        ConfigurationSection section = main.getConfig().getConfigurationSection("mineworld.messages");
        ENTERED_WORLD = Util.color(section.getString("enteredworld"));
        LEFT_WORLD = Util.color(section.getString("leftworld"));
        WORLD_STARTING_IN_30 = Util.color(section.getString("worldstartingin30"));
        WORLD_STARTING_IN_10 = Util.color(section.getString("worldstartingin10"));
        WORLD_STARTING_IN_5 = Util.color(section.getString("worldstartingin5"));
        ENDING_IN_10 = Util.color(section.getString("worldendingin10"));
        ENDING_IN_5 = Util.color(section.getString("worldendingin5"));
        ENDING_IN_1 = Util.color(section.getString("worldendingin1"));
        WORLD_OPEN = Util.color(section.getString("worldopen"));
        WORLD_CLOSED = Util.color(section.getString("worldclosed"));
        NO_ITEMS = Util.color(section.getString("noitems"));
        INVALID_COMMAND = Util.color(section.getString("invalidcommand"));
        NO_PERMISSION = Util.color(section.getString("nopermission"));
        WORLD_NOT_OPEN = Util.color(section.getString("worldnotopen"));
        NOT_IN_WORLD = Util.color(section.getString("notinworld"));
        COMMAND_BLOCKED = Util.color(section.getString("commandblocked"));
        CANT_BUILD = Util.color(section.getString("cantbuild"));
        setHelp();
    }

    private void setHelp()
    {
        help.clear();
        help.add("§7-----------------§bMineWorld§7-----------------");
        help.add("§b/mw loot create (name) (chance) §8- §fCreates a new loot §7(*mineworld.loot.create)");
        help.add("§b/mw loot edit (name) [chance] §8- §fEdit items or chance of a loot §7(*mineworld.loot.edit)");
        help.add("§b/mw loot additem (name) (chance) §8- §fAdds item with chance to loot §7(*mineworld.loot.additem)");
        help.add("§b/mw setkit §8- §fSets mineworld kit §7(*mineworld.setkit)");
        help.add("§b/mw setworld [name]§8- §fSets mineworld to spesified world or whatever world you are in §7(*mineworld.setworld)");
        help.add("§b/mw start §8- §fOpens the mineworld §7(*mineworld.open)");
        help.add("§b/mw stop §8- §fCloses the mineworld §7(*mineworld.close)");
        help.add("§b/mw enter [player] §8- §fMoves specified player or yourself to the mineworld §7(*mineworld.enter)");
        help.add("§b/mw leave §8- §fLeaves the mineworld §7(*mineworld.leave)");
    }
}
