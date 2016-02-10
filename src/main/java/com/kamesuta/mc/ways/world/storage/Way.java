package com.kamesuta.mc.ways.world.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Way implements Serializable {
	private final ArrayList<Node> ways;

	public int size() {
		return ways.size();
	}

	public Way(ArrayList<Node> ways)
	{
		this.ways = ways;
	}

	public Way(Collection<Node> ways)
	{
		this(new ArrayList<Node>(ways));
	}

	public Way(Node[] ways)
	{
		this(Arrays.asList(ways));
	}

	public Way()
	{
		this(new ArrayList<Node>());
	}

	public ArrayList<Node> waylist()
	{
		return ways;
	}
}
