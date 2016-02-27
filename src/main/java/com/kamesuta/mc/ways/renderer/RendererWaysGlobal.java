package com.kamesuta.mc.ways.renderer;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.ways.Ways;
import com.kamesuta.mc.ways.proxy.ClientProxy;
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
            if ((way != null && Ways.proxy.isRecording)/* || ClientProxy.isRenderingGuide*/) {
            	render(way, event.partialTicks, player);
            }
			this.profiler.endSection();
		}
	}

	public void render(Way way, float partialTicks, EntityPlayerSP player) {
		double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
		double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
		double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		if (Ways.proxy.isDepth)
			GL11.glDisable(GL11.GL_DEPTH_TEST);
		else
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glLineWidth(1f);
		GL11.glPointSize(2f);

//		GL11.glPushMatrix();
		GL11.glTranslated(-x, -y, -z);
		GL11.glTranslated(0.5, -0.5, 0.5);

		GL11.glColor4f(1f, 1f, 1f, 0.75f);

//		double pitch = ConfigurationHandler.wayOffset;
//
		RenderingBuffer path = way.getRenderingWayPath();

//		GL11.glTranslated(0.5f, 0.5f, 0.5f);
//		GL11.glBegin(GL11.GL_LINE_STRIP);

		glEnableClientState(GL_VERTEX_ARRAY);

		path.render(GL11.GL_LINE_STRIP);

		glDisableClientState(GL_VERTEX_ARRAY);

//		List<Vector3f> point = way.getRenderingWayPoint(mode);

//		for (ListIterator<Vector3f> it = path.listIterator(); it.hasNext();)
//		{
//			Vector3f now = it.next();
//			GL11.glVertex3d(now.getX()+0.5, now.getY()+pitch, now.getZ()+0.5);
//		}
//		GL11.glEnd();

//		GL11.glEnable(GL11.GL_DEPTH_TEST);
//		GL11.glColor4f(1f, 1f, 1f, 0.5f);
/*		GL11.glBegin(GL11.GL_POINTS);
		for (ListIterator<Vector3f> it = point.listIterator(); it.hasNext();)
		{
			Vector3f now = it.next().toVector3f();
			GL11.glVertex3d(now.getX()+0.5, now.getY()+pitch, now.getZ()+0.5);
		}
		GL11.glEnd();
*/
		// cleanup
		GL11.glEnable(GL11.GL_TEXTURE_2D);
//		GL11.glPopMatrix();
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

		GL11.glColor4f(1f, 1f, 1f, 0.25f);
		double pitch = ConfigurationHandler.wayOffset;
		ArrayList<Node> waylist = way.waylist();
		GL11.glEnable(GL11.GL_MAP1_VERTEX_3);
		for (int i = 0; i < waylist.size()-7; i += 2)
		{
			renderBezier(waylist.subList(i, i+3));
//			GL11.glVertex3d(n.getX()+0.5, n.getY()+pitch, n.getZ()+0.5);
		}

		GL11.glColor4f(1f, 1f, 1f, 0.3f);
		GL11.glBegin(GL11.GL_POINTS);
		for (Node n : way.waylist())
		{
			GL11.glVertex3d(n.getX()+0.5, n.getY()+pitch, n.getZ()+0.5);
		}
		GL11.glEnd();

		// cleanup
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}

	private void renderBezier(List<Node> waylist) {
		FloatBuffer buf = BufferUtils.createFloatBuffer(waylist.size() * 6);
		for (int i = 0; i < waylist.size(); i ++) {
			Node n = waylist.get(i);
			buf.put(i*3, (float) n.getX());
			buf.put(i*3+1, (float) n.getY());
			buf.put(i*3+2, (float) n.getZ());
			buf.put(i*3+3, (float) n.getX());
			buf.put(i*3+4, (float) n.getY());
			buf.put(i*3+5, (float) n.getZ());
		}
		GL11.glMap1f(GL11.GL_MAP1_VERTEX_3, 0.0f, 1.0f, 3, 4, buf);
		GL11.glBegin(GL11.GL_LINE_STRIP);
		for (int i = 0; i < 24; i ++) {
			GL11.glEvalCoord1f(i / 24.0f);
		}
		GL11.glEnd();
	}
*/
}
