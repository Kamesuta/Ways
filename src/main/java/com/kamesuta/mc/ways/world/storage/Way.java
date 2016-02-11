package com.kamesuta.mc.ways.world.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.kamesuta.mc.ways.util.vector.Vector3l;

public class Way implements Serializable {
	private final ArrayList<Vector3l> ways;

	public int size() {
		return ways.size();
	}

	public Way(ArrayList<Vector3l> ways)
	{
		this.ways = ways;
	}

	public Way(Collection<Vector3l> ways)
	{
		this(new ArrayList<Vector3l>(ways));
	}

	public Way(Vector3l[] ways)
	{
		this(Arrays.asList(ways));
	}

	public Way()
	{
		this(new ArrayList<Vector3l>());
	}

	public ArrayList<Vector3l> waylist()
	{
		return ways;
	}
}
