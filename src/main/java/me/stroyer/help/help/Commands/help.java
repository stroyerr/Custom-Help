package me.stroyer.help.help.Commands;

import me.stroyer.help.help.GUI.HelpMenu;
import me.stroyer.help.help.GUI.NewItem;
import me.stroyer.help.help.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class help implements CommandExecutor {

    private final Main main;
    public help(Main main){this.main = main;}


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){return false;}
        HelpMenu.open((Player) sender);
        return true;
    }

}
