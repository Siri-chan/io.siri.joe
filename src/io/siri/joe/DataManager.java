package io.siri.joe;

import java.io.*;

/**
 * JOE's inbuilt Data Manager, handles Serialisation and I/O.
 * @author Siri
 */
public class DataManager {
    Config cfg;
    public String constantDataPath;
    public DataManager(Config c){
        cfg = c;
        String[] paths = {System.getenv("LOCALAPPDATA") + "/" + cfg.author + "/" + cfg.pkgid,
                System.getenv("HOME") + "/.config/joe/" + cfg.author + "/" + cfg.pkgid,
                System.getenv("LOCALAPPDATA") +  cfg.author + "/" + cfg.pkgid,
                "./" + cfg.author + "/" + cfg.pkgid
        };
        for (String s : paths) {
            var f = new File(s);
            if (f.exists())
                constantDataPath = s;
            if (f.mkdir())
                constantDataPath = s;
        }
        if(constantDataPath == null){
            //FIXME: This straight doesn't work.
            System.out.println("[Non-Fatal Error] DataManager failed to Initialise; Could not initialise constant data path.");
            constantDataPath = "";
        }
    }

    /**
     * Serializes any data.
     * @param data Data Class to be serialised.
     * @apiNote Ensure any confidential data (as well as anything else that shouldn't be saved) within the class T is marked as transient.
     * @param path The path to be serialized to. Relative to `joe.DataManager.constantDataPath` Must include a Filename and Extension.
     */
    public <T extends Serializable> void save(T data, String path){
        try {
            File f;
            String s = "";

            FileOutputStream fileOut =
                    new FileOutputStream(s + path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data to %s\n", s + path);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * DeSerializes any data.
     * @apiNote Ensure any transient data within the class T will not load.
     * @param path The path to deserialize from. Relative to `joe.DataManager.constantDataPath` Must include a Filename and Extension.
     */
    public <T extends Serializable> T load(String path){
        T data;
        try {
            FileInputStream fileIn = new FileInputStream(constantDataPath + path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = (T) in.readObject();
            in.close();
            fileIn.close();
            System.out.printf("Loaded Data from %s\n", constantDataPath + path);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }
}
