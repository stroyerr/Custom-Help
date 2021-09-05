package me.stroyer.help.help;

import me.stroyer.help.help.Commands.help;
import me.stroyer.help.help.Listeners.HelpItemEvent;
import me.stroyer.help.help.Listeners.InventoryTake;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("help").setExecutor(new help(this));
        getServer().getPluginManager().registerEvents(new InventoryTake(), this);
        getServer().getPluginManager().registerEvents(new HelpItemEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
