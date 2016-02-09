package com.kamesuta.mc.ways.proxy;

import java.io.File;
import java.io.IOException;

import com.kamesuta.mc.ways.handler.ConfigurationHandler;
import com.kamesuta.mc.ways.reference.Reference;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy {
    public boolean isRecording = false;
    public boolean isSaveEnabled = true;
    public boolean isLoadEnabled = true;

	public void preInit(FMLPreInitializationEvent event) {
		Reference.logger = event.getModLog();
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
	}

	public void init(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

	public void serverStarting(FMLServerStartingEvent event) {
	}

	public void createFolders() {
		if (!ConfigurationHandler.waysDirectory.exists()) {
			if (!ConfigurationHandler.waysDirectory.mkdirs()) {
				Reference.logger.warn("Could not create ways directory [{}]!",
						ConfigurationHandler.waysDirectory.getAbsolutePath());
			}
		}
	}

	public abstract File getDataDirectory();

	public File getDirectory(final String directory) {
		final File dataDirectory = getDataDirectory();
		final File subDirectory = new File(dataDirectory, directory);

		if (!subDirectory.exists()) {
			if (!subDirectory.mkdirs()) {
				Reference.logger.error("Could not create directory [{}]!", subDirectory.getAbsolutePath());
			}
		}

		try {
			return subDirectory.getCanonicalFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return subDirectory;
	}

    public void newWay() {
    }

    public void unloadWay() {
    }

    public abstract boolean saveWay(File directory, String filename);

	public abstract boolean loadWay(EntityPlayer player, File directory, String filename);

	public abstract boolean isPlayerQuotaExceeded(EntityPlayer player);

	public abstract File getPlayerWaysDirectory(EntityPlayer player, boolean privateDirectory);
}
