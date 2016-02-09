package com.kamesuta.mc.ways.handler;

import java.io.File;
import java.io.IOException;

import com.kamesuta.mc.ways.Ways;
import com.kamesuta.mc.ways.reference.Names;
import com.kamesuta.mc.ways.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationHandler {
	public static final ConfigurationHandler INSTANCE = new ConfigurationHandler();

	public static final String VERSION = "1";

	public static Configuration configuration;

	public static final String WAYS_DIRECTORY_STR = "ways";
	public static final File WAYS_DIRECTORY_DEFAULT = new File(Ways.proxy.getDataDirectory(), WAYS_DIRECTORY_STR);
	public static final int PLAYER_QUOTA_KILOBYTES_DEFAULT = 8192;
	public static final double WAY_OFFSET_DEFAULT = 0.5;

	public static File waysDirectory = WAYS_DIRECTORY_DEFAULT;
	public static int playerQuotaKilobytes = PLAYER_QUOTA_KILOBYTES_DEFAULT;
	public static double wayOffset = WAY_OFFSET_DEFAULT;

	public static Property propWaysDirectory = null;
	public static Property propPlayerQuotaKilobytes = null;
	public static Property propWayOffset = null;

	public static void init(File configFile) {
		if (configuration == null) {
			configuration = new Configuration(configFile, VERSION);
			loadConfiguration();
		}
	}

	public static void loadConfiguration() {
		propWaysDirectory = configuration.get(Names.Config.Category.GENERAL, Names.Config.WAYS_DIRECTORY,
				WAYS_DIRECTORY_STR, Names.Config.WAYS_DIRECTORY_DESC);
		propWaysDirectory.setLanguageKey(Names.Config.LANG_PREFIX + "." + Names.Config.WAYS_DIRECTORY);
		waysDirectory = new File(propWaysDirectory.getString());

		try {
			waysDirectory = waysDirectory.getCanonicalFile();
			final String waysPath = waysDirectory.getAbsolutePath();
			final String dataPath = Ways.proxy.getDataDirectory().getAbsolutePath();
			if (waysPath.contains(dataPath)) {
				propWaysDirectory
						.set(waysPath.substring(dataPath.length()).replace("\\", "/").replaceAll("^/+", ""));
			} else {
				propWaysDirectory.set(waysPath.replace("\\", "/"));
			}
		} catch (IOException e) {
			Reference.logger.warn("Could not canonize path!", e);
		}

		propPlayerQuotaKilobytes = configuration.get(Names.Config.Category.SERVER, Names.Config.PLAYER_QUOTA_KILOBYTES,
				PLAYER_QUOTA_KILOBYTES_DEFAULT, Names.Config.PLAYER_QUOTA_KILOBYTES_DESC);
		propPlayerQuotaKilobytes.setLanguageKey(Names.Config.LANG_PREFIX + "." + Names.Config.PLAYER_QUOTA_KILOBYTES);
		playerQuotaKilobytes = propPlayerQuotaKilobytes.getInt(PLAYER_QUOTA_KILOBYTES_DEFAULT);

		propWayOffset = configuration.get(Names.Config.Category.RENDER, Names.Config.WAY_OFFSET,
				WAY_OFFSET_DEFAULT, Names.Config.WAY_OFFSET_DESC);
		propWayOffset.setLanguageKey(Names.Config.LANG_PREFIX + "." + Names.Config.WAY_OFFSET);
		propWayOffset.setMaxValue(2.0);
		propWayOffset.setMinValue(0);
		wayOffset = propWayOffset.getDouble();

		Ways.proxy.createFolders();

		if (configuration.hasChanged()) {
			configuration.save();
		}
	}

	private ConfigurationHandler() {
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(Reference.MODID)) {
			loadConfiguration();
		}
	}
}