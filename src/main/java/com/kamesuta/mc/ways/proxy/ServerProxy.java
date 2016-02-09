package com.kamesuta.mc.ways.proxy;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.kamesuta.mc.ways.handler.ConfigurationHandler;
import com.kamesuta.mc.ways.reference.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class ServerProxy extends CommonProxy {
    @Override
    public File getDataDirectory() {
        final File file = MinecraftServer.getServer().getFile(".");
        try {
            return file.getCanonicalFile();
        } catch (IOException e) {
            Reference.logger.info("Could not canonize path!", e);
        }
        return file;
    }

    @Override
    public boolean loadWay(EntityPlayer player, File directory, String filename) {
        return false;
    }

    @Override
    public boolean isPlayerQuotaExceeded(EntityPlayer player) {
        int spaceUsed = 0;

        //Space used by private directory
        File waysDirectory = getPlayerWaysDirectory(player, true);
        spaceUsed += getSpaceUsedByDirectory(waysDirectory);

        //Space used by public directory
        waysDirectory = getPlayerWaysDirectory(player, false);
        spaceUsed += getSpaceUsedByDirectory(waysDirectory);
        return ((spaceUsed / 1024) > ConfigurationHandler.playerQuotaKilobytes);
    }

    private int getSpaceUsedByDirectory(File directory) {
        int spaceUsed = 0;
        //If we don't have a player directory yet, then they haven't uploaded any files yet.
        if (directory == null || !directory.exists()) {
            return 0;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            files = new File[0];
        }
        for (File path : files) {
            spaceUsed += path.length();
        }
        return spaceUsed;
    }

    @Override
    public File getPlayerWaysDirectory(EntityPlayer player, boolean privateDirectory) {
        final UUID playerId = player.getUniqueID();
        if (playerId == null) {
            Reference.logger.warn("Unable to identify player {}", player.toString());
            return null;
        }

        File playerDir = new File(ConfigurationHandler.waysDirectory.getAbsolutePath(), playerId.toString());
        if (privateDirectory) {
            return new File(playerDir, "private");
        } else {
            return new File(playerDir, "public");
        }
    }

	@Override
	public boolean saveWay(File directory, String filename) {
		return false;
	}
}
