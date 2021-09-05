package me.stroyer.help.help.TicketFramework;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class TicketMessage {
    public static void sendMembers(Player sender, List<Player> members, String message){
        String senderName = sender.getName();

        for(int i = 0; i < members.size(); i ++){
            members.get(i).sendMessage(ChatColor.GOLD + "LonelyMC" + ChatColor.GRAY + " Support Ticket " + ChatColor.RED + "// " + ChatColor.WHITE + senderName + ": " + ChatColor.BLUE + message);
        }
    }
}
