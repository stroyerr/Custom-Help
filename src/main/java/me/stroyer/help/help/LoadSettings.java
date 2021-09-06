package me.stroyer.help.help;

import org.bukkit.entity.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadSettings {
    public static void load() throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream("./plugins/Help/playerData.txt");
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        ArrayList<PlayerSettings> playerSettingsList = (ArrayList<PlayerSettings>) oIn.readObject();
        PlayerSettings.playerSettingsList = playerSettingsList;
        oIn.close();
    }
}
