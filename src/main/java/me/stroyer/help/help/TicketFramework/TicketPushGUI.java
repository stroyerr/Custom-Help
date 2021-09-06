package me.stroyer.help.help.TicketFramework;

import me.stroyer.help.help.GUI.Methods.FillBlank;
import me.stroyer.help.help.GUI.NewItem;
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

public class TicketPushGUI {

    public static Inventory inv;
    public static ItemStack accept;
    public static ItemStack ignore;
    public static Ticket ticket;

    public static void send(Player p, Ticket t){
        inv = Bukkit.createInventory(null, 27, t.playerCreated.getName() + ChatColor.RED + " opened a ticket.");
        ItemStack info = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.GOLD + "Ticket Details: ", "");

        List<String> ticketDetails = new ArrayList<String>();
        ItemMeta infoMeta = info.getItemMeta();
        ticket = t;
        ticketDetails.add(ChatColor.LIGHT_PURPLE + "Player: " + ChatColor.DARK_RED +t.playerCreated.getName());
        ticketDetails.add(ChatColor.LIGHT_PURPLE + "Priority: " + ChatColor.DARK_RED + "Average");
        ticketDetails.add(ChatColor.LIGHT_PURPLE + "Ticket members: " + ChatColor.DARK_RED + t.members.size());

        infoMeta.setLore(ticketDetails);
        info.setItemMeta(infoMeta);

        accept = NewItem.createGuiItem(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "Claim Ticket");
        ignore = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Ignore Ticket");


        inv.setItem(11, ignore);
        inv.setItem(13, info);
        inv.setItem(15, accept);

        inv = FillBlank.getNewInv(inv);

        p.openInventory(inv);

    }

    public static void InventoryInteractEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(accept)){
            e.getWhoClicked().closeInventory();
            Player p = (Player) e.getWhoClicked();
            Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "" + p.getName() + " has entered a support ticket. This player will not be able to see chat messages for the duration of the ticket.");
            p.getInventory().setItem(0, Ticket.getStaffControler());
            if(ticket.staffHost == null){
                ticket.staffHost = p;
                TicketMessage.sendMembers(p, ticket.members, p.getName() + ChatColor.RED + " has joined the ticket as the host.");
                ticket.members.add(p);
            }else{
                ticket.members.add(p);
                TicketMessage.sendMembers(p, ticket.members, p.getName() + ChatColor.RED + " has joined the ticket as a support member.");
            e.getWhoClicked().closeInventory();
            }
        }

        if(e.getCurrentItem().equals(ignore)){
            e.getWhoClicked().closeInventory();
        }
    }
}
