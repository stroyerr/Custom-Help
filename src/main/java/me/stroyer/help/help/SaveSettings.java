package me.stroyer.help.help;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SaveSettings {
    public static void save() throws IOException {
        FileOutputStream fOut = new FileOutputStream("./plugins/Help/playerData.txt");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        ArrayList<PlayerSettings> ps = PlayerSettings.playerSettingsList;
        oOut.writeObject(ps);
        oOut.close();
    }
}
