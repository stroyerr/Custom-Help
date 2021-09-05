package me.stroyer.help.help.GUI.Methods;

import me.stroyer.help.help.GUI.NewItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FillBlank {
    public static Inventory getNewInv(Inventory inv){
        ItemStack placeholder = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.DARK_PURPLE+"Made for LonelyMC", ChatColor.RED + "Developed by Stroyer_");
        for(int i = 0; i < inv.getSize(); i++){
            if(inv.getItem(i) == null){
                inv.setItem(i, placeholder);
            }
        }
        return inv;
    }
}
