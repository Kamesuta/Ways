package com.kamesuta.mc.ways.renderer;

import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.ways.handler.ConfigurationHandler;
import com.kamesuta.mc.ways.proxy.ClientProxy;
import com.kamesuta.mc.ways.world.storage.Node;
import com.kamesuta.mc.ways.world.storage.Way;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.client.event.RenderWorldLastEvent;

@SideOnly(Side.CLIENT)
public class RendererWaysGlobal {
	public static final RendererWaysGlobal INSTANCE = new RendererWaysGlobal();
	private final Profiler profiler = ClientProxy.MINECRAFT.mcProfiler;

	private RendererWaysGlobal() {
	}

	@SubscribeEvent
	public void onRender(RenderWorldLastEvent event) {
		EntityPlayerSP player = ClientProxy.MINECRAFT.thePlayer;
		if (player != null) {
			this.profiler.startSection("ways");
            Way way = ClientProxy.way;
            if ((way != null/* && schematic.isRendering*/)/* || ClientProxy.isRenderingGuide*/) {
            	render(way, event.partialTicks, player);
            }
			this.profiler.endSection();
		}
	}

	// public void render(EntityPlayerSP player) {
	// GL11.glPushMatrix();
	// GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	// GL11.glEnable(GL11.GL_BLEND);
	//
	// GL11.glTranslated(-playerPosition.x, -playerPosition.y,
	// -playerPosition.z);
	//
	// }
	public void render(Way way, float partialTicks, EntityPlayerSP player) {
		double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
		double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
		double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glLineWidth(1f);
		GL11.glPointSize(2f);

		GL11.glPushMatrix();
		GL11.glTranslated(-x, -y, -z);

		GL11.glColor4f(1f, 1f, 1f, 0.15f);
		GL11.glBegin(GL11.GL_LINE_STRIP);
		double pitch = ConfigurationHandler.wayOffset;
		for (Iterator<Node> it = way.iterator(); it.hasNext();)
		{
			Node now = it.next();
			GL11.glVertex3d(now.getX()+0.5, now.getY()+pitch, now.getZ()+0.5);
		}
		GL11.glEnd();

		GL11.glColor4f(1f, 1f, 1f, 0.3f);
		GL11.glBegin(GL11.GL_POINTS);
		for (Iterator<Node> it = way.iterator(); it.hasNext();)
		{
			Node now = it.next();
			GL11.glVertex3d(now.getX()+0.5, now.getY()+pitch, now.getZ()+0.5);
		}
		GL11.glEnd();

		// cleanup
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
/*	public void render(Way way, float partialTicks, EntityPlayerSP player) {
		double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
		double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
		double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glLineWidth(1f);
		GL11.glPointSize(2f);

		GL11.glPushMatrix();
		GL11.glTranslated(-x, -y, -z);

		GL11.glColor4f(1f, 1f, 1f, 0.15f);
		GL11.glBegin(GL11.GL_LINE_STRIP);
		double pitch = ConfigurationHandler.wayOffset;
		for (Iterator<Node> it = way.iterator(); it.hasNext();)
		{
			Node now = it.next();
			GL11.glVertex3d(now.getX()+0.5, now.getY()+pitch, now.getZ()+0.5);
		}
		GL11.glEnd();

		GL11.glColor4f(1f, 1f, 1f, 0.3f);
		GL11.glBegin(GL11.GL_POINTS);
		for (Iterator<Node> it = way.iterator(); it.hasNext();)
		{
			Node now = it.next();
			GL11.glVertex3d(now.getX()+0.5, now.getY()+pitch, now.getZ()+0.5);
		}
		GL11.glEnd();

		// cleanup
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}*/
}
