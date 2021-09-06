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
            if(activeTickets.get(i).playerCreated == null){
                activeTickets.remove(i);
            }
            if(activeTickets.size() > 1){
                p.sendMessage(ChatColor.RED + "Cannot create ticket as the maximum number of active tickets is currently reached. Please try again shortly.");
                return;
            }
            if(activeTickets.size() > 0){
                if(activeTickets.get(i).playerCreated != null){
                    if(activeTickets.get(i).playerCreated.equals(p)){
                        p.sendMessage(ChatColor.RED + "Cannot create a new ticket while you are already in an active ticket.");
                        return;
                    }
                }
            }


        }
        Ticket ticket = new Ticket(p);
        activeTickets.add(ticket);
        push(ticket);
        p.getInventory().setItem(7, Ticket.getPlayerController());
        Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has opened a support ticket. This player will not be able to see chat messages for the duration of the ticket.");
    }

    public static void push(Ticket ticket){
        List<Player> staffOnline = new ArrayList<Player>();
        staffOnline = Staff.get();
        for(int i = 0; i < staffOnline.size(); i++){
            TicketPushGUI.send(staffOnline.get(i), ticket);
        }
    }
}
