package me.stroyer.help.help.TicketFramework.Controllers;

import me.stroyer.help.help.TicketFramework.Ticket;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ListenForGUIOpen implements Listener {


    @EventHandler
    public static void onUse(PlayerInteractEvent e){
        if(e.getItem().equals(Ticket.getStaffControler())){
            e.setCancelled(true);
            StaffController.open(e);
        }

        if(e.getItem().equals(Ticket.getPlayerController())){
            e.setCancelled(true);
            PlayerController.open(e);
        }
    }


}
