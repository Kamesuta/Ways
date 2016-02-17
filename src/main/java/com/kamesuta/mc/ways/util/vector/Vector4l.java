package com.kamesuta.mc.ways.util.vector;

public class Vector4l extends Vector3l implements Vector4 {
	public long w;

	public Vector4l() {
		this(0, 0, 0, 0);
	}

	public Vector4l(Vector4l vec) {
		this(vec.x, vec.y, vec.z, vec.w);
	}

	public Vector4l(long num) {
		this(num, num, num, num);
	}

	public Vector4l(long x, long y, long z, long w) {
		super(x, y, z);
		this.w = w;
	}

	public final long getW() {
		return this.w;
	}

	public final void setW(long w) {
		this.w = w;
	}

	public Vector4l set(Vector4l vec) {
		return set(vec.x, vec.y, vec.z, vec.w);
	}

	public Vector4l set(long x, long y, long z, long w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}

	@Override
	public long lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}

	public final double lengthTo(Vector4l vec) {
		return Math.sqrt(lengthSquaredTo(vec));
	}

	public long lengthSquaredTo(Vector4l vec) {
		return pow2(this.x - vec.x) + pow2(this.y - vec.y) + pow2(this.z - vec.z) + pow2(this.w - vec.w);
	}

	@Override
	public Vector4l negate() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		this.w = -this.w;
		return this;
	}

	public double dot(Vector4l vec) {
		return this.x * vec.x + this.y * vec.y + this.z * vec.z + this.w * vec.w;
	}

	@Override
	public Vector4l scale(double scale) {
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
		this.w *= scale;
		return this;
	}

	public Vector4l add(Vector4l vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		this.w += vec.w;
		return this;
	}

	public Vector4l add(long x, long y, long z, long w) {
		this.x += x;
		this.y += y;
		this.z += z;
		this.w += w;
		return this;
	}

	public Vector4l sub(Vector4l vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
		this.w -= vec.w;
		return this;
	}

	public Vector4l sub(long x, long y, long z, long w) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		this.w -= w;
		return this;
	}

	@Override
	public Vector4i toVector4i() {
		return new Vector4i((int) Math.floor(this.x), (int) Math.floor(this.y), (int) Math.floor(this.z),
				(int) Math.floor(this.w));
	}

	@Override
	public Vector4i toVector4i(Vector4i vec) {
		return vec.set((int) Math.floor(this.x), (int) Math.floor(this.y), (int) Math.floor(this.z),
				(int) Math.floor(this.w));
	}

	@Override
	public Vector4l toVector4l() {
		return this;
	}

	@Override
	public Vector4l toVector4l(Vector4l vec) {
		return vec.set(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vector4f toVector4f() {
		return new Vector4f(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vector4f toVector4f(Vector4f vec) {
		return vec.set(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vector4d toVector4d() {
		return new Vector4d(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vector4d toVector4d(Vector4d vec) {
		return vec.set(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vector4l clone() {
		return new Vector4l(this);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Vector4l && equals((Vector4l) obj);
	}

	public boolean equals(Vector4l vec) {
		return this.x == vec.x && this.y == vec.y && this.z == vec.z && this.w == vec.w;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s, %s, %s]", this.x, this.y, this.z, this.w);
	}
}
