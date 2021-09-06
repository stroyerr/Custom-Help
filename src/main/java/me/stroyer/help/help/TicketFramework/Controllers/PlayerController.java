package me.stroyer.help.help.TicketFramework.Controllers;

import me.stroyer.help.help.GUI.Methods.FillBlank;
import me.stroyer.help.help.GUI.NewItem;
import me.stroyer.help.help.TicketFramework.CreateTicket;
import me.stroyer.help.help.TicketFramework.Ticket;
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

public class PlayerController {

    public static Ticket t;

    public static Inventory inv;
    public static ItemStack leave;

    public static void open(PlayerInteractEvent e){
        inv = Bukkit.createInventory(null, 27, "Ticket options");
        ItemStack details = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.GOLD + "Ticket Details");
        leave = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Leave Ticket");

        ItemMeta im = details.getItemMeta();
        List<String> lore = new ArrayList<String>();

        for (int i = 0; i < CreateTicket.activeTickets.size(); i++){
            Player p = e.getPlayer();
            for(int j = 0; j < CreateTicket.activeTickets.get(i).members.size(); j ++){
                if(p.equals(CreateTicket.activeTickets.get(i).members.get(j))){
                    t = Ticket.activeTickets.get(i);
                }
            }
        }

        lore.add(ChatColor.BLUE + "Members: " + ChatColor.WHITE + t.members.size());
        lore.add(ChatColor.BLUE + "Player who opened: " + ChatColor.WHITE + t.playerCreated.getName());

        im.setLore(lore);
        details.setItemMeta(im);

        inv.setItem(12, details);
        inv.setItem(14, leave);

        inv = FillBlank.getNewInv(inv);

        e.getPlayer().openInventory(inv);
    }

    public static void InventoryInteractEvent(InventoryClickEvent e){

    }
}
