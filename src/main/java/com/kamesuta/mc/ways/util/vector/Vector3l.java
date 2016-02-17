package com.kamesuta.mc.ways.util.vector;

public class Vector3l extends Vector2l implements Vector3 {
	public long z;

	public Vector3l() {
		this(0, 0, 0);
	}

	public Vector3l(Vector3l vec) {
		this(vec.x, vec.y, vec.z);
	}

	public Vector3l(long num) {
		this(num, num, num);
	}

	public Vector3l(long x, long y, long z) {
		super(x, y);
		this.z = z;
	}

	public Vector3l(Vector3 vec) {
		vec.toVector3l(this);
	}

	public final long getZ() {
		return this.z;
	}

	public final void setZ(long z) {
		this.z = z;
	}

	public Vector3l set(Vector3l vec) {
		return set(vec.x, vec.y, vec.z);
	}

	public Vector3l set(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public long lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	public final double lengthTo(Vector3l vec) {
		return Math.sqrt(lengthSquaredTo(vec));
	}

	public long lengthSquaredTo(Vector3l vec) {
		return pow2(this.x - vec.x) + pow2(this.y - vec.y) + pow2(this.z - vec.z);
	}

	@Override
	public Vector3l negate() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		return this;
	}

	public double dot(Vector3l vec) {
		return this.x * vec.x + this.y * vec.y + this.z * vec.z;
	}

	@Override
	public Vector3l scale(double scale) {
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
		return this;
	}

	public Vector3l add(Vector3l vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		return this;
	}

	public Vector3l add(long x, long y, long z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector3l sub(Vector3l vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
		return this;
	}

	public Vector3l sub(long x, long y, long z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	@Override
	public Vector3i toVector3i() {
		return new Vector3i((int) this.x, (int) this.y, (int) this.z);
	}

	@Override
	public Vector3i toVector3i(Vector3i vec) {
		return vec.set((int) this.x, (int) this.y, (int) this.z);
	}

	@Override
	public Vector3l toVector3l() {
		return this;
	}

	@Override
	public Vector3l toVector3l(Vector3l vec) {
		return vec.set(this.x, this.y, this.z);
	}

	@Override
	public Vector3f toVector3f() {
		return new Vector3f(this.x, this.y, this.z);
	}

	@Override
	public Vector3f toVector3f(Vector3f vec) {
		return vec.set(this.x, this.y, this.z);
	}

	@Override
	public Vector3d toVector3d() {
		return new Vector3d(this.x, this.y, this.z);
	}

	@Override
	public Vector3d toVector3d(Vector3d vec) {
		return vec.set(this.x, this.y, this.z);
	}

	@Override
	public Vector3l clone() {
		return new Vector3l(this);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Vector3l && equals((Vector3l) obj);
	}

	public boolean equals(Vector3l vec) {
		return this.x == vec.x && this.y == vec.y && this.z == vec.z;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s, %s]", this.x, this.y, this.z);
	}
}
