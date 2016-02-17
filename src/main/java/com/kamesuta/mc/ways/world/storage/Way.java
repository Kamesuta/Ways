package com.kamesuta.mc.ways.world.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.kamesuta.mc.ways.renderer.RenderingPath;
import com.kamesuta.mc.ways.util.vector.Vector3f;
import com.kamesuta.mc.ways.util.vector.Vector3l;

public class Way implements Serializable {
	private final ArrayList<Vector3l> way;
	private ArrayList<Vector3f> renderingwaypath;
	private ArrayList<Vector3f> renderingwaypoint;
	public static final int limit = 5*5;
	public static final int near = 10;

	public int size() {
		return way.size();
	}

	public Way(ArrayList<Vector3l> way)
	{
		this.way = way;
	}

	public Way(Collection<Vector3l> way)
	{
		this(new ArrayList<Vector3l>(way));
	}

	public Way(Vector3l[] way)
	{
		this(Arrays.asList(way));
	}

	public Way()
	{
		this(new ArrayList<Vector3l>());
	}

	public ArrayList<Vector3l> getWay()
	{
		return way;
	}

	public ArrayList<Vector3f> getRenderingWayPath(RenderingPath algorithm)
	{
		if (renderingwaypath != null && way.size() == renderingwaypath.size())
		{
			return renderingwaypath;
		} else {
			return renderingwaypath = algorithm.path(way, limit);
		}
	}

	public ArrayList<Vector3f> getRenderingWayPoint(RenderingPath algorithm)
	{
		if (renderingwaypoint != null && way.size() == renderingwaypoint.size())
		{
			return renderingwaypoint;
		} else {
			return renderingwaypoint = algorithm.point(way, limit);
		}
	}
}
