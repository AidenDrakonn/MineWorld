package me.drakonn.inferno.encryptedcodes;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.encryptedcodes.command.Command;
import me.drakonn.inferno.encryptedcodes.datamanagers.MessageManager;
import me.drakonn.inferno.encryptedcodes.listener.VoucherUseListener;
import me.drakonn.inferno.encryptedcodes.vouchers.VoucherManager;

public class EncryptedCodes {

    private VoucherManager voucherManager;
    private MessageManager messageManager;

    private Main main;
    public EncryptedCodes(Main main) {
        this.main = main;
    }

    public void load() {
        voucherManager = new VoucherManager(main);
        messageManager = new MessageManager(main);
        messageManager.loadMessages();
        main.getCommand("encryptedcodes").setExecutor(new Command());
        main.getServer().getPluginManager().registerEvents(new VoucherUseListener(), main);
        voucherManager.loadVouchers();
    }

    public void unLoad(){
        voucherManager.saveVouchers();
    }
}
