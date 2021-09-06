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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ActionsGUI {
    public static Ticket t;
    public static Inventory inv;
    public static ItemStack back;
    public static ItemStack playerInfo;
    public static ItemStack tpToPlayer;

    public static void open(InventoryClickEvent e){
        t = null;
        Player p = (Player) e.getWhoClicked();

        for(int i = 0; i < CreateTicket.activeTickets.size(); i++){
            if(CreateTicket.activeTickets.get(i).members.contains(p)){
                t = CreateTicket.activeTickets.get(i);
                break;
            }
        }

        inv = Bukkit.createInventory(null, 36, ChatColor.GREEN + "Actions");

        back = NewItem.createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "Back");
        tpToPlayer = NewItem.createGuiItem(Material.ENDER_PEARL, ChatColor.GREEN + "TP To " + t.playerCreated.getName());
        playerInfo = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.BLUE + t.playerCreated.getName());

        double x = ((double)Math.round(t.playerCreated.getLocation().getX()) * 10d) / 10d;
        double z = ((double)Math.round(t.playerCreated.getLocation().getZ()) * 10d) / 10d;

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "World: " + ChatColor.YELLOW + t.playerCreated.getWorld().toString());
        lore.add(ChatColor.GOLD + "Location: " + ChatColor.YELLOW + x + ", " + z);
        lore.add(ChatColor.GOLD + "Priority: " + ChatColor.YELLOW + "Average");
        lore.add(ChatColor.GOLD + "Is Staff: " + ChatColor.YELLOW + t.playerCreated.hasPermission("help.staff"));

        ItemMeta im = playerInfo.getItemMeta();
        im.setLore(lore);
        playerInfo.setItemMeta(im);

        inv.setItem(12, playerInfo);
        inv.setItem(14, tpToPlayer);
        inv.setItem(27, back);

        inv = FillBlank.getNewInv(inv);

        e.getWhoClicked().openInventory(inv);
    }

    public static void InventoryInteractEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(back)){
            e.getWhoClicked().openInventory(StaffController.inv);
        }
        if(e.getCurrentItem().equals(tpToPlayer)){
            Player p = (Player) e.getWhoClicked();
            p.teleport(t.playerCreated.getLocation());
            e.getWhoClicked().closeInventory();
            TicketMessage.sendMembers(p, t.members, ChatColor.GREEN + p.getName() + " teleported to " + t.playerCreated.getName());
        }
    }
}
