package me.stroyer.help.help.Commands;

import me.stroyer.help.help.GUI.HelpMenu;
import me.stroyer.help.help.GUI.NewItem;
import me.stroyer.help.help.Items.HelpItem;
import me.stroyer.help.help.Main;
import me.stroyer.help.help.PlayerSettings;
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
        Player p = (Player) sender;
        if(args.length == 0){
            HelpMenu.open((Player) sender);
        }

        if(args.length == 1 && p.hasPermission("help.staff")){
            if(args[0].equalsIgnoreCase("save")){
                PlayerSettings.save();
            }
        }
        if(args.length < 1){return true;}
        if(args[0].equalsIgnoreCase("toggle")){
                for(int i = 0; i < PlayerSettings.playerSettingsList.size(); i++){
                    if (PlayerSettings.playerSettingsList.get(i).UUID.equals(p.getUniqueId())) {
                        PlayerSettings.playerSettingsList.get(i).forceItem = !(PlayerSettings.playerSettingsList.get(i).forceItem);
                        p.sendMessage(ChatColor.GOLD + "Set setting (force help item) to: " + PlayerSettings.playerSettingsList.get(i).forceItem);
                        return true;
                    }
                }
                PlayerSettings ps = new PlayerSettings(p, false);
                PlayerSettings.addNewPlayerSetting(ps);
                PlayerSettings.save();
                p.sendMessage(ChatColor.GOLD + "Set setting (force help item) to: " + ps.forceItem);
        }
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("debug") && p.hasPermission("help.staff")){
                p.sendMessage("Your UUID: " + p.getUniqueId() +  " and your UUID in list: " + PlayerSettings.playerSettingsList.get(0).UUID);
                p.sendMessage("Currently " + PlayerSettings.playerSettingsList.size() + " player settings saved with your force toggle set to " + PlayerSettings.playerSettingsList.get(0).forceItem);
            }
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("get") && p.hasPermission("help.staff")){
                p.getInventory().setItem(8, HelpItem.get());
            }
        }

        return true;
    }

}
