package me.stroyer.help.help;

import me.stroyer.help.help.Commands.help;
import me.stroyer.help.help.Listeners.HelpItemEvent;
import me.stroyer.help.help.Listeners.InventoryTake;
import me.stroyer.help.help.TicketFramework.ChatHandler.ChatListener;
import me.stroyer.help.help.TicketFramework.Controllers.ListenForGUIOpen;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Main extends JavaPlugin {

    static File f = new File("./plugins/Help/playerData.txt");

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("help").setExecutor(new help(this));
        getServer().getPluginManager().registerEvents(new InventoryTake(), this);
        getServer().getPluginManager().registerEvents(new HelpItemEvent(), this);
        getServer().getPluginManager().registerEvents(new ListenForGUIOpen(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getLogger().info("Finished loading listeners and catchers, attempting to load data.");


        try{
            Path path = Paths.get("./plugins/Help");
            Files.createDirectories(path);
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PlayerSettings.load();
        Bukkit.getLogger().info("Successfuly loaded player settings from storage.");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
