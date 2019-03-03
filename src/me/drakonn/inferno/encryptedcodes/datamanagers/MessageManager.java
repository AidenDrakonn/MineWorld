package me.drakonn.inferno.encryptedcodes.datamanagers;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.utils.Util;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    public static String INVALID_COMMAND;
    public static String NO_PERMISSION;
    public static String RECIEVED_VOUCHER;
    public static List<String> help = new ArrayList<>();

    private Main main;
    public MessageManager(Main main)
    {
        this.main = main;
    }

    public void loadMessages()
    {
        ConfigurationSection section = main.getConfig().getConfigurationSection("encryptedcodes.messages");
        INVALID_COMMAND = Util.color(section.getString("invalidcommand"));
        NO_PERMISSION = Util.color(section.getString("nopermission"));
        RECIEVED_VOUCHER = Util.color(section.getString("recievedvoucher"));
        setHelp();
    }

    private void setHelp()
    {
        help.clear();
        help.add("§7-----------------§dEncryptedCodes§7-----------------");
        help.add("§b/voucher give (player) (voucher) [amount §8- §fGives the specified player a code §7(*encryptedcodes.give)");
    }
}
