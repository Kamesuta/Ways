package com.kamesuta.mc.ways.proxy;

import java.io.File;
import java.io.IOException;

import javax.vecmath.Vector3d;

import com.kamesuta.mc.ways.handler.ConfigurationHandler;
import com.kamesuta.mc.ways.handler.client.InputHandler;
import com.kamesuta.mc.ways.handler.client.OverlayHandler;
import com.kamesuta.mc.ways.handler.client.TickHandler;
import com.kamesuta.mc.ways.reference.Reference;
import com.kamesuta.mc.ways.renderer.RendererWaysGlobal;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class ClientProxy extends CommonProxy {
	public static final Vector3d playerPosition = new Vector3d();
	public static ForgeDirection orientation = ForgeDirection.UNKNOWN;
	public static int rotationRender = 0;

	public static MovingObjectPosition movingObjectPosition = null;

	private static final Minecraft MINECRAFT = Minecraft.getMinecraft();

	public static void setPlayerData(EntityPlayer player, float partialTicks) {
		playerPosition.x = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
		playerPosition.y = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
		playerPosition.z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

		orientation = getOrientation(player);

		rotationRender = MathHelper.floor_double(player.rotationYaw / 90) & 3;
	}

	private static ForgeDirection getOrientation(EntityPlayer player) {
		if (player.rotationPitch > 45) {
			return ForgeDirection.DOWN;
		} else if (player.rotationPitch < -45) {
			return ForgeDirection.UP;
		} else {
			switch (MathHelper.floor_double(player.rotationYaw / 90.0 + 0.5) & 3) {
			case 0:
				return ForgeDirection.SOUTH;
			case 1:
				return ForgeDirection.WEST;
			case 2:
				return ForgeDirection.NORTH;
			case 3:
				return ForgeDirection.EAST;
			}
		}

		return ForgeDirection.UNKNOWN;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		for (KeyBinding keyBinding : InputHandler.KEY_BINDINGS) {
			ClientRegistry.registerKeyBinding(keyBinding);
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);

		FMLCommonHandler.instance().bus().register(InputHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(TickHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(ConfigurationHandler.INSTANCE);

		MinecraftForge.EVENT_BUS.register(RendererWaysGlobal.INSTANCE);
		MinecraftForge.EVENT_BUS.register(new OverlayHandler());
	}

	@Override
	public File getDataDirectory() {
		final File file = MINECRAFT.mcDataDir;
		try {
			return file.getCanonicalFile();
		} catch (IOException e) {
			Reference.logger.debug("Could not canonize path!", e);
		}
		return file;
	}

	@Override
	public boolean isPlayerQuotaExceeded(EntityPlayer player) {
		return false;
	}

	@Override
	public File getPlayerWaysDirectory(EntityPlayer player, boolean privateDirectory) {
		return ConfigurationHandler.waysDirectory;
	}
}