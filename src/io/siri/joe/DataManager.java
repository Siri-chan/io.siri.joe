package io.siri.joe;

import java.io.*;
import java.nio.file.*;

/**
 * JOE's inbuilt Data Manager, handles Serialisation and I/O.
 * @author Siri
 */
public class DataManager {
    Config cfg;
    public Path constantDataPath = Paths.get("");
    public Path assetPath;
    public Path serialPath;
    public DataManager(Config c){
        cfg = c;
        String[] paths = {System.getenv("LOCALAPPDATA") + "\\" + cfg.author + "\\" + cfg.pkgid,
                System.getenv("HOME") + "/.config/joe/" + cfg.author + "/" + cfg.pkgid,
                "./" + cfg.author + "/" + cfg.pkgid
        };

        for (String s : paths) {
            //System.out.println("S " + s);
            File f;
            Path p = Paths.get(s);
            boolean set = false;
            try {
                f = p.toRealPath().toFile();
                //System.out.println("P " + p.toRealPath());
            } catch (IOException e) {
                //System.out.println("P ex " + p.toAbsolutePath());
                f = p.toAbsolutePath().toFile();
            }
            //System.out.println("F " + f);
            if(f.mkdirs() || f.exists()) { //was using mkdir, needed mkdirs, which is equivalent to `mkdir -p`
                constantDataPath = p;
                //System.out.println("SET F " + f);
                set = true;
            }
            if(set) break;
        }
        if(constantDataPath == Paths.get("")){
            Core.LogError("(Non-Fatal) DataManager failed to Initialise; Could not initialise constant data path.");
        }
        assetPath = new File(constantDataPath.toFile(), "assets").toPath();
        serialPath = new File(constantDataPath.toFile(), "serial").toPath();
        assetPath.toFile().mkdirs(); serialPath.toFile().mkdirs();
    }

    /**
     * Serializes any data.
     * @param data Data Class to be serialised.
     * @apiNote Ensure any confidential data (as well as anything else that shouldn't be saved) within the class T is marked as transient.
     * @param path The path to be serialized to. Relative to constantDataPath Must include a Filename and Extension.
     * @throws FileNotFoundException When Path not found
     */
    public <T extends Serializable> void save(T data, String path) throws FileNotFoundException{
        try {
            FileOutputStream fileOut = new FileOutputStream(serialPath + path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
            Core.Log("Serialized data to " + serialPath + path);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * DeSerializes any data.
     * @apiNote Ensure any transient data within the class T will not load.
     * @param path The path to deserialize from. Relative to `joe.DataManager.constantDataPath` Must include a Filename and Extension.
     * @throws FileNotFoundException When Input File not found
     */
    public <T extends Serializable> T load(String path) throws FileNotFoundException{
        T data;
        try {
            FileInputStream fileIn = new FileInputStream(serialPath + path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = (T) in.readObject();
            in.close();
            fileIn.close();
            Core.Log("Loaded Data from " + serialPath + path);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    /**
     * DeSerializes any data.
     * @apiNote Ensure any transient data within the class T will not load.
     * @param path The path to deserialize from. Relative to `joe.DataManager.constantDataPath` Must include a Filename and Extension.
     */
    public SoundAsset getSoundAsset(String path) throws InvalidAssetFileException {
        SoundAsset data = new SoundAsset(assetPath + path);
        Core.Log("Loaded Sound from " + assetPath + path);
        return data;
    }

    /**
     * DeSerializes any data.
     * @apiNote Ensure any transient data within the class T will not load.
     * @param path The path to deserialize from. Relative to `joe.DataManager.constantDataPath` Must include a Filename and Extension.
     */
    public SpriteAsset getSpriteAsset(String path){
        SpriteAsset data = new SpriteAsset(assetPath + path);
        Core.Log("Loaded Sprite from " + assetPath + path);
        return data;
    }
}
