package me.drakonn.inferno.encryptedcodes.listener;

import me.drakonn.inferno.encryptedcodes.vouchers.Voucher;
import me.drakonn.inferno.encryptedcodes.vouchers.VoucherManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class VoucherUseListener implements Listener {

    @EventHandler
    public void onVoucherUse(PlayerInteractEvent event){
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_AIR))
            return;

        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if(item == null || item.getType() == Material.AIR)
            return;

        Voucher voucher = VoucherManager.getVoucher(item);
        if(voucher == null)
            return;

        voucher.run(player.getName());
        ItemStack newItem = voucher.getItem();
        int amount = item.getAmount()-1;
        if(amount == 0)
            player.setItemInHand(null);
        else {
            newItem.setAmount(amount);
            player.setItemInHand(newItem);
        }
    }
}
