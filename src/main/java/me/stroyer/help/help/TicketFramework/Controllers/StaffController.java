package me.stroyer.help.help.TicketFramework.Controllers;

import me.stroyer.help.help.GUI.Methods.FillBlank;
import me.stroyer.help.help.GUI.NewItem;
import me.stroyer.help.help.TicketFramework.CreateTicket;
import me.stroyer.help.help.TicketFramework.Ticket;
import me.stroyer.help.help.TicketFramework.TicketMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StaffController {

    public static Inventory inv;
    public static ItemStack close;
    public static ItemStack addMember;
    public static ItemStack removeMember;
    public static ItemStack leave;
    public static ItemStack actions;

    public static void InventoryInteractEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(close)){
            for (int i = 0; i < CreateTicket.activeTickets.size(); i++){
                Player p = (Player) e.getWhoClicked();
                if(p.equals(CreateTicket.activeTickets.get(i).staffHost)){
                    Ticket t = CreateTicket.activeTickets.get(i);
                    TicketMessage.sendMembers(p, t.members, p.getName() + "Has closed this ticket.");
                    e.getWhoClicked().closeInventory();
                    Ticket.close(t);
                    return;
                }
            }
        }
        Player p = (Player) e.getWhoClicked();
        p.sendMessage(ChatColor.RED + "You are not the host of this ticket!");
        e.getWhoClicked().closeInventory();
    }

    public static void open(PlayerInteractEvent e){
        inv = Bukkit.createInventory(null, 54, "Ticket Controller");
        close = NewItem.createGuiItem(Material.REDSTONE_BLOCK, ChatColor.RED + "Close Ticket");
        addMember = NewItem.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + "Add Member to Ticket");
        removeMember = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.DARK_RED + "Remove Member from Ticket");
        leave = NewItem.createGuiItem(Material.TRIPWIRE_HOOK, ChatColor.GOLD + "Leave Ticket");
        ItemStack closeMenu = NewItem.createGuiItem(Material.BARRIER, "Close Menu");
        ItemStack ticketInfo = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.GREEN + "Ticket Details");

        actions = NewItem.createGuiItem(Material.REDSTONE, "Actions");

        List<String> ticketDetails = new ArrayList<String>();

        Ticket t = null;

        for (int i = 0; i < CreateTicket.activeTickets.size(); i++){
            Player p = e.getPlayer();
            for(int j = 0; j < CreateTicket.activeTickets.get(i).members.size(); j ++){
                if(p.equals(CreateTicket.activeTickets.get(i).members.get(j))){
                    t = Ticket.activeTickets.get(i);
                }
            }
        }

        ticketDetails.add(ChatColor.BLUE + "Members: " + ChatColor.WHITE + t.members.size());
        ticketDetails.add(ChatColor.BLUE + "Host: " + ChatColor.WHITE + t.staffHost.getName());
        ticketDetails.add(ChatColor.BLUE + "Player who opened: " + ChatColor.WHITE + t.playerCreated.getName());

        ItemMeta im = ticketInfo.getItemMeta();
        List<String> lore = im.getLore();
        lore = ticketDetails;
        im.setLore(lore);
        ticketInfo.setItemMeta(im);

        inv.setItem(12, ticketInfo);
        inv.setItem(14, actions);
        inv.setItem(28, addMember);
        inv.setItem(30, removeMember);
        inv.setItem(32, close);
        inv.setItem(34, leave);
        inv.setItem(53, closeMenu);

        inv = FillBlank.getNewInv(inv);

        e.getPlayer().openInventory(inv);


    }
}
