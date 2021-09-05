package me.stroyer.help.help.TicketFramework;

import me.stroyer.help.help.GUI.NewItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    public ItemStack staffController;

    public static List<Ticket> activeTickets = new ArrayList<Ticket>();

    public static List<Player> members = new ArrayList<Player>();

    public static Player staffHost;

    public static Player playerCreated;

    public Ticket(Player p){
        this.playerCreated = p;
        members.add(p);
        activeTickets.add(this);
    }

    public static ItemStack getStaffControler(){
        ItemStack staffController = NewItem.createGuiItem(Material.PAPER, ChatColor.RED + "Ticket Manager", "Use this item to manage an active ticket.");
        return staffController;
    }
}
