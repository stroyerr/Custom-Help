package me.stroyer.help.help.TicketFramework.ChatHandler;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.stroyer.help.help.TicketFramework.CreateTicket;
import me.stroyer.help.help.TicketFramework.Ticket;
import me.stroyer.help.help.TicketFramework.TicketMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.List;

public class ChatListener implements Listener {


    @EventHandler
    public static void onChatSend(AsyncPlayerChatEvent e){
        List<Ticket> activeTickets = CreateTicket.activeTickets;
        Player p = e.getPlayer();
        Bukkit.getLogger().info("recipents: " + e.getRecipients().size());
        for(int i = 0; i < activeTickets.size(); i++){
            for(int j = 0; j < activeTickets.get(i).members.size(); j++){
                if(activeTickets.get(i).members.get(j).equals(p)){
                    String originalMessage = e.getMessage();
                    e.setCancelled(true);
                    TicketMessage.sendMembers(p, activeTickets.get(i).members, originalMessage);
                    return;
                }else{
                    if(activeTickets.get(i).members != null){
                        e.getRecipients().remove(activeTickets.get(i).members.get(j));
                    }
                }
            }
        }
    }

}
