package me.stroyer.help.help.Listeners;

import me.stroyer.help.help.Items.HelpItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HelpItemEvent implements Listener {
    @EventHandler
    public static void onUse(PlayerInteractEvent e){
        if(e.getItem() == null){return;}
        if (e.getItem().equals(HelpItem.get())){
            e.setCancelled(true);
            e.getPlayer().performCommand("help");
        }
    }

    @EventHandler
    public static void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Inventory inv = p.getInventory();
        if(inv.getItem(8).equals(HelpItem.get())){
            return;
        }else{
            ItemStack safeItem = inv.getItem(8);
            for(int i = 0; i <= 35; i ++ ){
                if(inv.getItem(i) == null){
                    p.sendMessage(ChatColor.YELLOW + "Your " + inv.getItem(8).getType().name() + " was moved to inv slot " + i + " safely.");
                    inv.setItem(i, inv.getItem(8));
                    inv.setItem(8, HelpItem.get());
                    return;
                }
            }
            p.sendMessage(ChatColor.RED + "Your " + inv.getItem(8).getType().name() + " was lost as you had no free inventory slots. Contact staff with a screenshot of this message if you believe there was an error.");
            Bukkit.getLogger().info("ITEM // " + p.getName() + " LOST ITEM " + inv.getItem(8).getType().name());
            inv.setItem(8, HelpItem.get());
        }
    }

    @EventHandler
    public static void onInvClick(InventoryClickEvent e){
        if(e.getCurrentItem().equals(HelpItem.get())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public static void onDropItem(PlayerDropItemEvent e){
        ItemStack item = e.getItemDrop().getItemStack();
        if (item.equals(HelpItem.get())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public static void onDeath(PlayerDeathEvent e){
        e.getEntity().getInventory().removeItem(HelpItem.get());
    }

    @EventHandler
    public static void onRespawn(PlayerRespawnEvent e){
        e.getPlayer().getInventory().setItem(8, HelpItem.get());
    }
}
