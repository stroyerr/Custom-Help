package me.stroyer.help.help.TicketFramework;

import me.stroyer.help.help.GUI.Methods.Staff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CreateTicket {

    public static List<Ticket> activeTickets = new ArrayList<Ticket>();

    public static void newTicket(Player p){
        for(int i = 0; i < activeTickets.size(); i++){
            if(activeTickets.get(i).playerCreated.equals(p)){
                p.sendMessage(ChatColor.RED + "Cannot create a new ticket while you are already in an active ticket.");
                return;
            }
        }
        Ticket ticket = new Ticket(p);
        activeTickets.add(ticket);
        push(ticket);
        Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "" + p.getName() + " has opened a support ticket. This player will not be able to see chat messages for the duration of the ticket.");
    }

    public static void push(Ticket ticket){
        List<Player> staffOnline = new ArrayList<Player>();
        staffOnline = Staff.get();
        for(int i = 0; i < staffOnline.size(); i++){
            TicketPushGUI.send(staffOnline.get(i), ticket);
        }
    }
}
