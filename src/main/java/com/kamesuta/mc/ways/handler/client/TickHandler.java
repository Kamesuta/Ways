package com.kamesuta.mc.ways.handler.client;

import com.kamesuta.mc.ways.Ways;
import com.kamesuta.mc.ways.proxy.ClientProxy;
import com.kamesuta.mc.ways.reference.Reference;
import com.kamesuta.mc.ways.world.storage.Node;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MathHelper;

public class TickHandler {
	public static final TickHandler INSTANCE = new TickHandler();

	private Node cache;

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
			ClientProxy.MINECRAFT.mcProfiler.startSection("ways");

			EntityPlayerSP player = ClientProxy.MINECRAFT.thePlayer;
			if (player != null && ClientProxy.way != null && Ways.proxy.isRecording)
			{
				Node now = new Node(MathHelper.floor_double_long(player.posX), MathHelper.floor_double_long(player.posY), MathHelper.floor_double_long(player.posZ));
				if (!now.equals(cache))
				{
					ClientProxy.way.waylist().add(now);
				}
				cache = now;

			}

			ClientProxy.MINECRAFT.mcProfiler.endSection();
		}
	}
}
