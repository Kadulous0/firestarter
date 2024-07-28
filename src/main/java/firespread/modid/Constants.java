package firespread.modid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Constants {
    public static final String MOD_ID = "firespread";
    public static final String MOD_NAME = "Firestarter";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final String CONFIG_RESOURCE_NAME = "firespread-default-config.json5";
    public static final String CONFIG_FILENAME = "firespread.json5";
    public static final Path CONFIG_FILE_PATH = Path.of("config", CONFIG_FILENAME);
}
