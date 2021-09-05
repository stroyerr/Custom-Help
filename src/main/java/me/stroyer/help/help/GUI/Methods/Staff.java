package me.stroyer.help.help.GUI.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Staff {
    public static List<Player> list;

    public static List<Player> get(){
        list = new ArrayList<>(Bukkit.getOnlinePlayers());
        List<Player> staff = new ArrayList<Player>();
        for(int i = 0; i < list.size(); i ++){
            if(list.get(i).hasPermission("help.staff")){
                staff.add(list.get(i));
            }
        }
        return staff;
    }
}
