package com.kamesuta.mc.ways.world.storage;

import java.io.Serializable;
import java.util.ArrayList;

import com.kamesuta.mc.ways.renderer.RenderingBuffer;
import com.kamesuta.mc.ways.renderer.RenderingPath;
import com.kamesuta.mc.ways.util.vector.Vector3f;

public class Way implements Serializable {
	private final ArrayList<Vector3f> way;
	private transient RenderingBuffer renderingwaypath = new RenderingBuffer();;
//	private transient List<Vector3f> renderingwaypoint;
	public static final int limit = 5*5;
	public static final int near = 10;

	public int size() {
		return way.size();
	}

	public Way(ArrayList<Vector3f> way)
	{
		this.way = way;
	}

	public Way()
	{
		this(new ArrayList<Vector3f>());
	}

	public ArrayList<Vector3f> getWay()
	{
		return way;
	}

	public void add(Vector3f node) {
//		if (renderingwaypath.size() < way.size()) {
//			RenderingPath.Cave.pathAll(way, renderingwaypath, limit);
//		}
		way.add(node);
		RenderingPath.Cave.path(node, renderingwaypath, limit);
	}

	public RenderingBuffer getRenderingWayPath()
	{
		return renderingwaypath;
	}
//
//	public List<Vector3f> getRenderingWayPoint(RenderingPath algorithm)
//	{
//		if (renderingwaypoint != null && way.size() == renderingwaypoint.size())
//		{
//			return renderingwaypoint;
//		} else {
//			return renderingwaypoint = algorithm.pointAll(way, limit);
//		}
//	}
}
