package com.kamesuta.mc.ways.renderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.client.event.RenderWorldLastEvent;

@SideOnly(Side.CLIENT)
public class RendererWaysGlobal {
	public static final RendererWaysGlobal INSTANCE = new RendererWaysGlobal();
	private final Minecraft minecraft = Minecraft.getMinecraft();
	private final Profiler profiler = this.minecraft.mcProfiler;

	private RendererWaysGlobal() {
	}

	@SubscribeEvent
	public void onRender(RenderWorldLastEvent event) {
		EntityPlayerSP player = this.minecraft.thePlayer;
		if (player != null) {
			this.profiler.startSection("ways");
			render(event.partialTicks, player);
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
	public void render(float partialTicks, EntityPlayerSP player) {
		double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
		double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
		double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

		GL11.glPushMatrix();
		GL11.glTranslated(-x, -y, -z); // go from cartesian x,y,z coordinates to
										// in-world x,y,z coordinates
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		double minX = 0 + 0.02;
		double maxX = 1 - 0.02;
		double maxY = 10 + 0.02;
		double minZ = 0 + 0.02;
		double maxZ = 1 - 0.02;

		// render an "X" using 2 lines at (0, 10, 0) in game
		GL11.glBegin(GL11.GL_LINES); // begin drawing lines defined by 2
										// vertices

		GL11.glColor4f(1f, 1f, 1f, 0.5f); // alpha must be > 0.1
		GL11.glVertex3d(maxX, maxY, maxZ);
		GL11.glVertex3d(minX, maxY, minZ);
		GL11.glVertex3d(maxX, maxY, minZ);
		GL11.glVertex3d(minX, maxY, maxZ);
		GL11.glVertex3d(maxX, maxY+1, minZ);
		GL11.glVertex3d(minX, maxY+1, maxZ);

		GL11.glEnd();

		// cleanup
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
}
