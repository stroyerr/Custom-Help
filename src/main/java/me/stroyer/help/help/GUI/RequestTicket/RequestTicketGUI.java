package me.stroyer.help.help.GUI.RequestTicket;

import me.stroyer.help.help.GUI.HelpMenu;
import me.stroyer.help.help.GUI.Methods.FillBlank;
import me.stroyer.help.help.GUI.Methods.Staff;
import me.stroyer.help.help.GUI.NewItem;
import me.stroyer.help.help.TicketFramework.CreateTicket;
import me.stroyer.help.help.TicketFramework.Ticket;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RequestTicketGUI {
    public static Inventory inv;
    public static ItemStack back;
    public static ItemStack cancel;
    public static ItemStack open;

    public static void open(Player p){
        inv = Bukkit.createInventory(null, 54, "Create a ticket");

        ItemStack staffOnline = NewItem.createGuiItem(Material.REDSTONE_TORCH, ChatColor.RED + "" + ChatColor.BOLD + "Staff online: " + ChatColor.GOLD + Staff.get().size());
        back = NewItem.createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "Back");

        cancel = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED  + "Cancel");
        open = NewItem.createGuiItem(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "Open Ticket");

        ItemStack playerDetails = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.AQUA + "The following details will be recorded:", "");

        ItemMeta detailsMeta = playerDetails.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.LIGHT_PURPLE + "Username: " + ChatColor.GOLD + p.getName());
        lore.add(ChatColor.LIGHT_PURPLE + "World: " + ChatColor.GOLD + p.getWorld().getName());
        lore.add(ChatColor.LIGHT_PURPLE + "Donation status: " + ChatColor.GOLD + "Not a donor");
        lore.add(ChatColor.LIGHT_PURPLE + "Priority Level: " + ChatColor.YELLOW + "Average");

        detailsMeta.setLore(lore);
        playerDetails.setItemMeta(detailsMeta);

        inv.setItem(13, playerDetails);

        inv.setItem(31, staffOnline);
        inv.setItem(29, cancel);
        inv.setItem(33, open);
        inv.setItem(45, back);

        inv = FillBlank.getNewInv(inv);

        p.openInventory(inv);
    }

    public static void InventoryInteractEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(back)){
            e.getWhoClicked().openInventory(HelpMenu.inv);
        }
        if(e.getCurrentItem().equals(open)){
            e.getWhoClicked().closeInventory();
            Player p = (Player) e.getWhoClicked();
            CreateTicket.newTicket(p);
        }
        if(e.getCurrentItem().equals(cancel)){
            e.getWhoClicked().closeInventory();
        }
    }
}
