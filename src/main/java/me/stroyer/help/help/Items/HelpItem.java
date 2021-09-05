package me.stroyer.help.help.Items;

import me.stroyer.help.help.GUI.NewItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HelpItem {
    public static ItemStack HelpItem;

    public static ItemStack get(){
        HelpItem = NewItem.createGuiItem(Material.TRIPWIRE_HOOK, ChatColor.GOLD + "Help", ChatColor.RED + "LonelyMC Custom Help Menu");
        return HelpItem;
    }
}
