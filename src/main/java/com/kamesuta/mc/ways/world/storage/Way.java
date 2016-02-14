package com.kamesuta.mc.ways.world.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ListIterator;

import com.kamesuta.mc.ways.util.vector.Vector3f;
import com.kamesuta.mc.ways.util.vector.Vector3l;

public class Way implements Serializable {
	private final ArrayList<Vector3l> way;

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

	private ArrayList<Vector3f> renderingway;
	public ArrayList<Vector3f> getRenderingWay()
	{
		if (renderingway != null && way.size() == renderingway.size())
		{
			return renderingway;
		} else {
			return renderingway = optimize(way);
		}
	}

	public static final int limit = 5*5;
	public static ArrayList<Vector3f> optimize(ArrayList<Vector3l> sourcelist)
	{
		ArrayList<Vector3f> out = new ArrayList<Vector3f>();
		ArrayList<Vector3l> source = new ArrayList<Vector3l>(sourcelist);
		for (ListIterator<Vector3l> ita = source.listIterator(); ita.hasNext();)
		{
			Vector3l a = ita.next();
			if(a != null)
			{
				int ni = 1;
				Vector3f nv = a.toVector3f();
				for (ListIterator<Vector3l> itb = source.listIterator(ita.nextIndex()); itb.hasNext();)
				{
					Vector3l b = itb.next();
					if(b != null)
					{
						if (a.lengthSquaredTo(b) < limit)
						{
							nv.add(b.toVector3f());
							ni++;
							itb.set(null);
						}
					}
				}
				out.add(nv.scale(1d/ni));
			}
		}
		return out;
	}
}
