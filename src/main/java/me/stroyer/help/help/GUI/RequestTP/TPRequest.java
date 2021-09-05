package me.stroyer.help.help.GUI.RequestTP;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class TPRequest {
    public static Player p;
    public static Boolean isOpen;
    public static World world;
    public static Location location;
    public static Player handler;

    public TPRequest(Player p){
        this.p = p;
        this.location = p.getLocation();
        this.world = p.getWorld();
        this.isOpen = true;
    }
}
