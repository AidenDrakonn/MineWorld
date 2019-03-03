package me.drakonn.inferno.encryptedcodes.command;

import me.drakonn.inferno.encryptedcodes.datamanagers.MessageManager;
import me.drakonn.inferno.encryptedcodes.vouchers.Voucher;
import me.drakonn.inferno.encryptedcodes.vouchers.VoucherManager;
import me.drakonn.inferno.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand {

    public void run(CommandSender sender, String[] args){
        if(!sender.hasPermission("encryptedcodes.give")){
            sender.sendMessage(MessageManager.NO_PERMISSION);
            return;
        }

        Player player = Bukkit.getPlayer(args[1]);
        if(player == null){
            sender.sendMessage(Util.color("&c&l(!) &c" + args[1] + " is not online"));
            return;
        }

        Voucher voucher = VoucherManager.getVoucher(args[2]);
        if(voucher == null){
            sender.sendMessage(Util.color("&c&l(!) &cThat voucher does not exist, do /encryptedcodes list to see all vouchers"));
            return;
        }

        ItemStack item = voucher.getItem();

        if(args.length == 4 && Util.isInt(args[3]))
            item.setAmount(Integer.valueOf(args[3]));

        Util.givePlayerItem(player, item);
        sender.sendMessage(Util.color("&8&l[&b&lEncryptedCodes&8&l] &fSucsesfully given "+args[1]+" a "+ args[2]+" code"));
        player.sendMessage(MessageManager.RECIEVED_VOUCHER);
    }
}
