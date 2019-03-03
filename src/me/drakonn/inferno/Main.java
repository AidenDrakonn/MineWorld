package me.drakonn.inferno;

import me.drakonn.inferno.encryptedcodes.EncryptedCodes;
import me.drakonn.inferno.mineworld.MineWorld;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static EncryptedCodes encryptedCodes;
    private static MineWorld mineWorld;
    private static Main instance;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        mineWorld = new MineWorld(this);
        encryptedCodes = new EncryptedCodes(this);
        encryptedCodes.load();
        mineWorld.load();
    }

    public void onDisable() {
        mineWorld.unLoad();
        encryptedCodes.unLoad();
        saveConfig();
    }

    public static EncryptedCodes getEncryptedCodes() {
        return encryptedCodes;
    }

    public static MineWorld getMineWorld() {
        return mineWorld;
    }

    public static Main getInstance() {
        return instance;
    }
}
