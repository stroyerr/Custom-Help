package me.stroyer.help.help.GUI.RequestTP;

import me.stroyer.help.help.GUI.HelpMenu;
import me.stroyer.help.help.GUI.Methods.FillBlank;
import me.stroyer.help.help.GUI.NewItem;
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

public class RequestTP {

    public static ItemStack setDescription;
    public static ItemStack confirm;
    public static ItemStack cancel;
    public static Inventory inv;
    public static ItemStack back;

    public static void open(Player p){
        inv = Bukkit.createInventory(null, 45, ChatColor.BLUE + "Request a staff to TP");
        setDescription = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.GOLD + "Online staff:");

        back = NewItem.createGuiItem(Material.BARRIER, ChatColor.DARK_RED + "Back");

        confirm = NewItem.createGuiItem(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "Request");
        cancel = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Cancel");

        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        List<String> staffOnline = new ArrayList<String>();

        for(int i = 0; i < players.size(); i++){
            if(players.get(i).hasPermission("help.staff"))
            {
                staffOnline.add(players.get(i).getName());
            }
        }

        ItemMeta onlineStaffMeta = setDescription.getItemMeta();
        onlineStaffMeta.setLore(staffOnline);
        setDescription.setItemMeta(onlineStaffMeta);

        for(int m = 0; m < staffOnline.size(); m ++){
        }



        inv.setItem(13, setDescription);
        inv.setItem(32, confirm);
        inv.setItem(30, cancel);
        inv.setItem(36, back);

        inv = FillBlank.getNewInv(inv);

        p.openInventory(inv);
    }

    public static void InventoryInteractEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(confirm)){

        }else if(e.getCurrentItem().equals(cancel)){
            e.getWhoClicked().closeInventory();
        }else if(e.getCurrentItem().equals(back)){
            e.getWhoClicked().openInventory(HelpMenu.inv);
        }else if(e.getCurrentItem().equals(confirm)){

        }
    }
}
