package firespread.modid;

import net.fabricmc.api.ModInitializer;

import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;

import com.google.gson.Gson;

import org.slf4j.Logger;

import static firespread.modid.Constants.*;

public class Firespread implements ModInitializer {
    public static final Logger LOGGER = LOG;
	public static FirespreadConfig config;

	@Override
	public void onInitialize() {
		LOGGER.info(MOD_NAME + " initializing");
		if (!CONFIG_FILE_PATH.toFile().exists()) {
			LOGGER.info("No config for " + MOD_NAME + " exists, creating copy of default config.");
			try (InputStream in = Firespread.class.getClassLoader().getResourceAsStream(CONFIG_RESOURCE_NAME)) {
				if (in == null) throw new IllegalStateException("Failed to load " + MOD_NAME + "'s default config \"" + CONFIG_RESOURCE_NAME +"\"");
				Files.createDirectories(CONFIG_FILE_PATH);
				Files.copy(in, CONFIG_FILE_PATH, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		try (final InputStream in = Files.newInputStream(CONFIG_FILE_PATH)) {
			config = new Gson().fromJson(new java.io.InputStreamReader(in), FirespreadConfig.class);
		} catch (Exception e) {
			throw new RuntimeException(MOD_NAME + "'s config file is malformed! If you don't know what's causing this, delete the config file and restart the game.");
		}
		LOGGER.info(MOD_NAME + " initialized");
	}
}