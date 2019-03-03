package me.drakonn.inferno.mineworld;

import me.drakonn.inferno.Main;
import me.drakonn.inferno.mineworld.command.Command;
import me.drakonn.inferno.mineworld.datamanagers.MessageManager;
import me.drakonn.inferno.mineworld.datamanagers.SettingsManager;
import me.drakonn.inferno.mineworld.kit.Kit;
import me.drakonn.inferno.mineworld.loot.LootEditListener;
import me.drakonn.inferno.mineworld.loot.LootManager;
import me.drakonn.inferno.mineworld.worldmanager.MineWorldManager;
import me.drakonn.inferno.mineworld.worldmanager.activation.PlayerCount;
import me.drakonn.inferno.mineworld.worldmanager.activation.Time;
import me.drakonn.inferno.mineworld.worldmanager.listeners.*;

public class MineWorld {

    private MessageManager messageManager;
    private SettingsManager settingsManager;
    private LootManager lootManager;
    private Kit kit;
    private Time time;
    private MineWorldManager mineWorldManager;
    private PlayerCount playerCount;

    private Main main;
    public MineWorld(Main main)
    {
        this.main = main;
    }

    public void load()
    {
        messageManager = new MessageManager(main);
        settingsManager = new SettingsManager(main);
        messageManager.loadMessages();
        settingsManager.loadSettings();
        lootManager = new LootManager(main);
        kit = new Kit(main);
        lootManager.loadLoot();
        kit.loadKit();
        mineWorldManager = new MineWorldManager(main);
        mineWorldManager.intialise();
        time = new Time();
        playerCount = new PlayerCount();
        time.start();
        playerCount.start();
        main.getCommand("mineworld").setExecutor(new Command());
        registerListeners();
    }

    public void unLoad()
    {
        MineWorldManager.endWorld();
        lootManager.saveLoot();
        kit.saveKit();
        mineWorldManager.save();
    }

    private void registerListeners(){
        main.getServer().getPluginManager().registerEvents(new BlockBreakListener(), main);
        main.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), main);
        main.getServer().getPluginManager().registerEvents(new CommandListener(), main);
        main.getServer().getPluginManager().registerEvents(new DeathListener(), main);
        main.getServer().getPluginManager().registerEvents(new PlayerLogoutListener(), main);
        main.getServer().getPluginManager().registerEvents(new LootCloseListener(), main);
        main.getServer().getPluginManager().registerEvents(new LootEditListener(), main);
    }
}
