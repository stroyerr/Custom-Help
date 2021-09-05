package me.stroyer.help.help.GUI.RequestTP;

import me.stroyer.help.help.GUI.Methods.FillBlank;
import me.stroyer.help.help.GUI.Methods.Staff;
import me.stroyer.help.help.GUI.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SendRequest {

    static List<Player> onlineStaff;
    public static TPRequest currentRequest;

    public static void open(TPRequest request){
        onlineStaff = new ArrayList<Player>();
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        for(int i = 0; i < players.size(); i ++){
            if(players.get(i).hasPermission("help.staff")){
                onlineStaff.add(players.get(i));
            }
        }

        push(request, onlineStaff);
        currentRequest = request;



    }

    public static Inventory inv;
    public static ItemStack accept;
    public static ItemStack deny;

    public static void push(TPRequest request, List<Player> staff){
        inv = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + request.p.getName() + ChatColor.WHITE + " requests help");

        ItemStack playerDetails = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.BLUE + request.p.getName(), "");

        List<String> playerData = new ArrayList<String>();

        playerData.add("World: " + ChatColor.GOLD + request.p.getWorld().getName());
        double x = ((double)Math.round(request.p.getLocation().getX()) * 10d) / 10d;
        double z = ((double)Math.round(request.p.getLocation().getZ()) * 10d) / 10d;
        playerData.add("Coordinates: " + ChatColor.GOLD + x + ", " + z);
        playerData.add("Request Claimed: " + ChatColor.DARK_RED + !request.isOpen);

        ItemMeta playerDetailsMeta = playerDetails.getItemMeta();
        playerDetailsMeta.setLore(playerData);
        playerDetails.setItemMeta(playerDetailsMeta);


        accept = NewItem.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + "Claim Request", "Claim this request and TP to the player.");
        deny = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.DARK_RED + "Ignore Request", "Ignore (" + ChatColor.YELLOW + onlineStaff.size() + " other online staff" + ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + ")" );

        inv.setItem(11, deny);
        inv.setItem(15, accept);

        inv.setItem(13, playerDetails);

        inv = FillBlank.getNewInv(inv);

        for(int i = 0; i < staff.size(); i ++){
            Player p = staff.get(i);
            p.openInventory(inv);
        }
    }

    public static void InventoryInteractEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(accept)){
            if(currentRequest.isOpen){
                currentRequest.handler = (Player) e.getWhoClicked();
                currentRequest.isOpen = false;
                List<Player> staffList = Staff.get();
                for(int i = 0; i < staffList.size(); i ++ ){
                    Player pl = (Player) e.getWhoClicked();
                    staffList.get(i).sendMessage(ChatColor.GREEN + "TP Request from " + ChatColor.YELLOW + currentRequest.p.getName() + ChatColor.GREEN + " is being handled by " + ChatColor.YELLOW + pl.getName());
                }
                currentRequest.handler.teleport(currentRequest.p.getLocation());

                e.getWhoClicked().closeInventory();
            }else{
                e.getWhoClicked().closeInventory();
                Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.RED + "Request already claimed by " + currentRequest.handler.getName());
            }
        }else if(e.getCurrentItem().equals(deny)){
            e.getWhoClicked().closeInventory();
        }
    }
}
