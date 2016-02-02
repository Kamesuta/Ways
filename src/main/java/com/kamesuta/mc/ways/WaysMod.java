package com.kamesuta.mc.ways;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kamesuta.mc.ways.renderer.RenderWays;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = WaysMod.modid, name = WaysMod.modname, version = WaysMod.modversion)
public class WaysMod {
	public static final String modid = "ways";
	public static final String modname = "Ways";
	public static final String modversion = "1.0";
	public static final Logger logger = LogManager.getLogger(modname);

	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.info("Welcome to ways.");
		//FMLCommonHandler.instance().bus().register(RenderWays.INSTANCE);
		MinecraftForge.EVENT_BUS.register(RenderWays.INSTANCE);
	}

	@NetworkCheckHandler
	public boolean netCheckHandler(Map<String, String> mods, Side side)
	{
		return true;
	}
}
