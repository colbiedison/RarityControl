package us.dison.rarityctl.config;

import com.google.gson.Gson;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Config {
    public static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "raritycontrol.json");
    private static Config INSTANCE;

    private final Rarities config;

    public Config(Rarities rarities) {
        this.config = rarities;
    }

    public static void init() {
        try {
            Gson gson = new Gson();
            String fileContents = FileUtils.readFileToString(CONFIG_FILE, StandardCharsets.UTF_8);
            Rarities rarities = gson.fromJson(fileContents, Rarities.class);
            INSTANCE = new Config(rarities);
        } catch (IOException e) {
            System.err.println("Failed to read config file: "+ CONFIG_FILE.getAbsolutePath());
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Configuration has not been initialized!");
        }
        return INSTANCE;
    }

    public Rarities getConfig() {
        return config;
    }
}
