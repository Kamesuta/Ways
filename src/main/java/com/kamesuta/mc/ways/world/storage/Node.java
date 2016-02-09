package com.kamesuta.mc.ways.world.storage;

import java.io.Serializable;

/**
 * 一点を扱います
 * @author Kamesuta
 */
public class Node implements Serializable {
	private final long x;
	private final long y;
	private final long z;

	public Node(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public long getX(Node base)
	{
		return base.x + getX();
	}

	public long getY(Node base)
	{
		return base.y + getY();
	}

	public long getZ(Node base)
	{
		return base.z + getZ();
	}

	public long getX()
	{
		return this.x;
	}

	public long getY()
	{
		return this.y;
	}

	public long getZ()
	{
		return this.z;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (x ^ (x >>> 32));
		result = prime * result + (int) (y ^ (y >>> 32));
		result = prime * result + (int) (z ^ (z >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}
}
