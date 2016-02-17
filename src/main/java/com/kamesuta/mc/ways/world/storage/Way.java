package com.kamesuta.mc.ways.world.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.kamesuta.mc.ways.renderer.RenderingPath;
import com.kamesuta.mc.ways.util.vector.Vector3f;

public class Way implements Serializable {
	private final ArrayList<Vector3f> way;
	private transient List<Vector3f> renderingwaypath;
	private transient List<Vector3f> renderingwaypoint;
	public static final int limit = 5*5;
	public static final int near = 10;

	public int size() {
		return way.size();
	}

	public Way(ArrayList<Vector3f> way)
	{
		this.way = way;
	}

	public Way(Collection<Vector3f> way)
	{
		this(new ArrayList<Vector3f>(way));
	}

	public Way(Vector3f[] way)
	{
		this(Arrays.asList(way));
	}

	public Way()
	{
		this(new ArrayList<Vector3f>());
	}

	public ArrayList<Vector3f> getWay()
	{
		return way;
	}

	public List<Vector3f> getRenderingWayPath(RenderingPath algorithm)
	{
		if (renderingwaypath != null && way.size() == renderingwaypath.size())
		{
			return renderingwaypath;
		} else {
			return renderingwaypath = algorithm.path(way, limit);
		}
	}

	public List<Vector3f> getRenderingWayPoint(RenderingPath algorithm)
	{
		if (renderingwaypoint != null && way.size() == renderingwaypoint.size())
		{
			return renderingwaypoint;
		} else {
			return renderingwaypoint = algorithm.point(way, limit);
		}
	}
}
