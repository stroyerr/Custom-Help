package me.stroyer.help.help.TicketFramework.Controllers;

import me.stroyer.help.help.GUI.Methods.FillBlank;
import me.stroyer.help.help.GUI.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AddMember {
    public static Inventory inv;
    public static ItemStack back;

    public static void open(InventoryClickEvent e){
        inv = Bukkit.createInventory(null, 45, "Coming soon!");
        ItemStack comingSoon = NewItem.createGuiItem(Material.REDSTONE_TORCH, "Coming soon!");
        back = NewItem.createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "Back");

        Player p = (Player) e.getWhoClicked();

        inv.setItem(13, comingSoon);
        inv.setItem(36, back);

        inv = FillBlank.getNewInv(inv);

        p.openInventory(inv);
    }

    public static void InventoryInteractEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(back)){
            Player p = (Player) e.getWhoClicked();
            p.openInventory(StaffController.inv);
        }
    }
}
