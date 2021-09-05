package me.stroyer.help.help.GUI;

import me.stroyer.help.help.GUI.RequestTP.RequestTP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HelpMenu {

    public static ItemStack requestTP;
    public static ItemStack requestTicket;

    public static Inventory inv;
    public static void open(Player p){
        inv = Bukkit.createInventory(null, 9, ChatColor.RED + "LonelyMC" + ChatColor.LIGHT_PURPLE + " Help");
        inv.addItem();

         requestTP = NewItem.createGuiItem(Material.ENDER_PEARL, ChatColor.BLUE+ "Request TP", "Request a staff member TP");
         requestTicket = NewItem.createGuiItem(Material.PAPER, ChatColor.RED+ "Open a Ticket", "Get in touch with a staff member.");
         ItemStack cmds = NewItem.createGuiItem(Material.REDSTONE, ChatColor.GOLD + "Command help", "Use /? [page number] for commands.");

         ItemStack placeholder = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.DARK_PURPLE+"Made for LonelyMC", ChatColor.RED + "Developed by Stroyer_");

        inv.setItem(2, requestTP);
        inv.setItem(4, cmds);
        inv.setItem(6, requestTicket);

        inv.setItem(1, placeholder);
        inv.setItem(3, placeholder);
        inv.setItem(0, placeholder);
        inv.setItem(5, placeholder);
        inv.setItem(7, placeholder);
        inv.setItem(8, placeholder);

        p.openInventory(inv);
    }

    public static void InteractEvent(InventoryClickEvent e){
        ItemStack itemClicked = e.getCurrentItem();
        if(itemClicked.equals(requestTP)){
            Player p = (Player) e.getWhoClicked();
            RequestTP.open(p);
        }else if(itemClicked.equals(requestTicket)){
        }
    }
}
