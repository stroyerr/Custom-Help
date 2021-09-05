package me.stroyer.help.help.Listeners;

import me.stroyer.help.help.Commands.help;
import me.stroyer.help.help.GUI.HelpMenu;
import me.stroyer.help.help.GUI.RequestTP.RequestTP;
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
    }
}
