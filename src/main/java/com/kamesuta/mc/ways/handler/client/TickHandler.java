package com.kamesuta.mc.ways.handler.client;

import com.kamesuta.mc.ways.reference.Reference;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.client.Minecraft;

public class TickHandler {
	public static final TickHandler INSTANCE = new TickHandler();

	private final Minecraft minecraft = Minecraft.getMinecraft();

	private TickHandler() {
	}

	@SubscribeEvent
	public void onClientConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
		Reference.logger.info("Scheduling client settings reset.");
	}

	@SubscribeEvent
	public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
		Reference.logger.info("Scheduling client settings reset.");
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			this.minecraft.mcProfiler.startSection("schematica");

			this.minecraft.mcProfiler.endSection();
		}
	}
}
