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

    public static ItemStack getPlayerController(){
        ItemStack playerController = NewItem.createGuiItem(Material.PAPER, ChatColor.GREEN + "Ticket Options");
        return playerController;
    }

    public static void close(Ticket t){
        CreateTicket.activeTickets.remove(t);
        for(int i = 0; i < t.members.size(); i ++ ){
            t.members.get(i).getInventory().removeItem(getStaffControler());
        }
        t = null;
        t.playerCreated = null;
        t.members = new ArrayList<Player>();
        t.staffHost = null;
    }

    public static void leave(Ticket t, Player p){
        if(t.staffHost.equals(p)){
            p.sendMessage(ChatColor.RED + "Cannot leave a ticket you are hosting. If the ticket is finished, instead close this ticket.");
            return;
        }else{
            t.members.remove(p);
            TicketMessage.sendMembers(p, t.members, ChatColor.RED + p.getName() + " has left the ticket.");
        }
    }
}
