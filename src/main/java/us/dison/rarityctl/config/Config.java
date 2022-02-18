package us.dison.rarityctl.config;

import com.google.gson.Gson;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import us.dison.rarityctl.RarityControl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Config {
    public static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "raritycontrol.json");
    private static final List<String> OVERRIDES = new ArrayList<>();
    private static Config INSTANCE;

    private final Rarities config;

    public Config(Rarities rarities) {
        this.config = rarities;
    }

    public static void init() {
        try {
            if (!CONFIG_FILE.exists()) { // Write the default config if it doesn't exist
                RarityControl.LOGGER.info("No config file! Writing default config to "+CONFIG_FILE.getAbsolutePath());
                InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("raritycontrol.json");
                OutputStream outputStream = new FileOutputStream(CONFIG_FILE);
                inputStream.transferTo(outputStream);
            }
        } catch (Exception e) {
            RarityControl.LOGGER.error("Failed to write default config file: "+CONFIG_FILE.getAbsolutePath());
            e.printStackTrace();
        }

        try {
            RarityControl.LOGGER.info("Reading config file");
            Gson gson = new Gson();
            String fileContents = FileUtils.readFileToString(CONFIG_FILE, StandardCharsets.UTF_8);
            Rarities rarities = gson.fromJson(fileContents, Rarities.class);
            RarityControl.LOGGER.info("Found "+rarities.getRarities().size()+" custom rarities");
            INSTANCE = new Config(rarities);

            if (rarities.getRarities() == null || rarities.getRarities().size() <= 0) {
                RarityControl.LOGGER.warn("RarityControl config file is empty! The mod will do nothing.");
                return;
            }
            for (int i = 0; i < rarities.getRarities().size(); i++) {
                RarityCustom rarity = rarities.getRarities().get(i);
                switch (rarity.getName()) {
                    case "COMMON", "UNCOMMON", "RARE", "EPIC" -> {
                        if (!OVERRIDES.contains(rarity.getName())) {
                            RarityControl.LOGGER.info("Overriding "+rarity.getName()+" rarity");
                            OVERRIDES.add(rarity.getName());
                        }
                    }
                }
            }
        } catch (IOException e) {
            RarityControl.LOGGER.error("Failed to read config file: "+ CONFIG_FILE.getAbsolutePath());
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Configuration has not been initialized!");
        }
        return INSTANCE;
    }

    public static List<String> getOverrides() {
        return OVERRIDES;
    }

    public Rarities getConfig() {
        return config;
    }
}
