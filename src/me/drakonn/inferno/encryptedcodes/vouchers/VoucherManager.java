package me.drakonn.inferno.encryptedcodes.vouchers;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.utils.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class VoucherManager {

    private static List<Voucher> vouchers = new ArrayList<>();

    private Main main;
    public VoucherManager(Main main){
        this.main = main;
    }

    public void loadVouchers() {
        if (!main.getConfig().contains("encryptedcodes.vouchers"))
            return;

        for (String name : main.getConfig().getConfigurationSection("encryptedcodes.vouchers").getKeys(false))
        {
            ConfigurationSection section = main.getConfig().getConfigurationSection("encryptedcodes.vouchers."+name);
            int lastFor = section.getInt("lastfor");
            List<String> atStart = new ArrayList<>();
            List<String> atEnd = new ArrayList<>();
            if(section.contains("atstart"))
                atStart = section.getStringList("atstart");
            if(section.contains("atend"))
                atEnd = section.getStringList("atend");
            ItemStack item = Util.getItem(section.getConfigurationSection("item"));
            Voucher voucher = new Voucher(name, item, lastFor, atStart, atEnd);
            vouchers.add(voucher);
            if(section.contains("inprogress")) {
                for (String string : section.getStringList("inprogress")) {
                    String username = string.split(":")[0];
                    Long time = Long.valueOf(string.split(":")[1]);
                    int timeRemaining = Math.round(lastFor - (System.currentTimeMillis() - time/1000));
                    voucher.resume(username, timeRemaining);
                }
            }
        }
    }

    public void saveVouchers()
    {
        System.out.println("[EncryptedCodes] Saving vouchers to config");
        for(Voucher voucher : vouchers)
        {
            ConfigurationSection section = main.getConfig().getConfigurationSection("encryptedcodes.vouchers."+voucher.getName());
            List<String> inProgress = new ArrayList<>();
            for(String username : voucher.data.keySet()){
                long time = voucher.data.get(username);
                inProgress.add(username+":"+Long.toString(time));
            }
            section.set("inprogress", inProgress);
        }
    }

    public static void addVoucher (Voucher voucher) {
        vouchers.add(voucher);
    }

    public static void removeVoucher (String name) {
        Voucher voucher = getVoucher(name);
        if(voucher != null)
            vouchers.remove(voucher);
    }

    public static Voucher getVoucher(String name) {
        return vouchers.stream().filter(voucher -> voucher.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static Voucher getVoucher(ItemStack item) {
        return vouchers.stream().filter(voucher -> voucher.getItem().isSimilar(item)).findFirst().orElse(null);
    }
}
