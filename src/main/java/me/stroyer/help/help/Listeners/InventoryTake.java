package me.stroyer.help.help.Listeners;

import me.stroyer.help.help.Commands.help;
import me.stroyer.help.help.GUI.HelpMenu;
import me.stroyer.help.help.GUI.RequestTP.RequestTP;
import me.stroyer.help.help.GUI.RequestTP.SendRequest;
import me.stroyer.help.help.GUI.RequestTicket.RequestTicketGUI;
import me.stroyer.help.help.TicketFramework.Controllers.ActionsGUI;
import me.stroyer.help.help.TicketFramework.Controllers.AddMember;
import me.stroyer.help.help.TicketFramework.Controllers.PlayerController;
import me.stroyer.help.help.TicketFramework.Controllers.StaffController;
import me.stroyer.help.help.TicketFramework.TicketPushGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryTake implements Listener {


    @EventHandler
    public void onPlayerInvInteract(InventoryClickEvent e){
        if(e.getInventory().equals(HelpMenu.inv)){
            e.setCancelled(true);
            HelpMenu.InteractEvent(e);
        }

        if(e.getInventory().equals(RequestTP.inv)){
            e.setCancelled(true);
            RequestTP.InventoryInteractEvent(e);
        }
        if(e.getInventory().equals(SendRequest.inv)){
            e.setCancelled(true);
            SendRequest.InventoryInteractEvent(e);
        }

        if(e.getInventory().equals(RequestTicketGUI.inv)){
            e.setCancelled(true);
            RequestTicketGUI.InventoryInteractEvent(e);
        }

        if(e.getInventory().equals(TicketPushGUI.inv)){
            e.setCancelled(true);
            TicketPushGUI.InventoryInteractEvent(e);
        }

        if(e.getInventory().equals(StaffController.inv)){
            e.setCancelled(true);
            StaffController.InventoryInteractEvent(e);
        }

        if(e.getInventory().equals(PlayerController.inv)){
            e.setCancelled(true);
            PlayerController.InventoryInteractEvent(e);
        }

        if(e.getInventory().equals(AddMember.inv)){
            e.setCancelled(true);
            AddMember.InventoryInteractEvent(e);
        }

        if(e.getInventory().equals(ActionsGUI.inv)){
            e.setCancelled(true);
            ActionsGUI.InventoryInteractEvent(e);
        }

    }
}
