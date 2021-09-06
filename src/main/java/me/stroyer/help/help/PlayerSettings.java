package me.stroyer.help.help;

import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerSettings implements Serializable {

    public static ArrayList<PlayerSettings> playerSettingsList = new ArrayList<PlayerSettings>();


    public Boolean forceItem;
    public UUID UUID;

    public PlayerSettings(Player player, Boolean forceItem){
        this.forceItem = forceItem;
        this.UUID = player.getUniqueId();
    }
    // Load existing settings from hard save. Should be used on enable.
    public static void load(){
        try {
            LoadSettings.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addNewPlayerSetting(PlayerSettings ps){
        playerSettingsList.add(ps);
        save();
    }

    public static void save(){
        try {
            SaveSettings.save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
