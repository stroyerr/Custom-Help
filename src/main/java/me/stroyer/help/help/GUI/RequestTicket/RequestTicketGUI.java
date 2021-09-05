package me.stroyer.help.help.GUI.RequestTicket;

import me.stroyer.help.help.GUI.HelpMenu;
import me.stroyer.help.help.GUI.Methods.FillBlank;
import me.stroyer.help.help.GUI.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RequestTicketGUI {
    public static Inventory inv;
    public static ItemStack back;

    public static void open(Player p){
        inv = Bukkit.createInventory(null, 27, "Create a ticket");

        ItemStack info = NewItem.createGuiItem(Material.REDSTONE_TORCH, ChatColor.RED + "" + ChatColor.BOLD + "Coming soon!");
        back = NewItem.createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "Back");

        inv.setItem(13, info);
        inv.setItem(18, back);

        inv = FillBlank.getNewInv(inv);

        p.openInventory(inv);
    }

    public static void InventoryInteractEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(back)){
            e.getWhoClicked().openInventory(HelpMenu.inv);
        }
    }
}
