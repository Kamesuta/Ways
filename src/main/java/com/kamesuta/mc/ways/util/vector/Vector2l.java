package com.kamesuta.mc.ways.util.vector;

public class Vector2l implements Vector2 {
	public long x;
	public long y;

	public Vector2l() {
		this(0, 0);
	}

	public Vector2l(Vector2l vec) {
		this(vec.x, vec.y);
	}

	public Vector2l(long num) {
		this(num, num);
	}

	public Vector2l(long x, long y) {
		this.x = x;
		this.y = y;
	}

	public final long getX() {
		return this.x;
	}

	public final long getY() {
		return this.y;
	}

	public final void setX(long x) {
		this.x = x;
	}

	public final void setY(long y) {
		this.y = y;
	}

	public Vector2l set(Vector2l vec) {
		return set(vec.x, vec.y);
	}

	public Vector2l set(long x, long y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public final double length() {
		return Math.sqrt(lengthSquared());
	}

	public long lengthSquared() {
		return this.x * this.x + this.y * this.y;
	}

	public final double lengthTo(Vector2l vec) {
		return Math.sqrt(lengthSquaredTo(vec));
	}

	public long lengthSquaredTo(Vector2l vec) {
		return pow2(this.x - vec.x) + pow2(this.y - vec.y);
	}

	protected final long pow2(long num) {
		return num * num;
	}

	public final Vector2l normalize() {
		double len = length();
		if (len != 0.0) {
			return scale(1.0 / len);
		}

		return this;
	}

	public Vector2l negate() {
		this.x = -this.x;
		this.y = -this.y;
		return this;
	}

	public double dot(Vector2l vec) {
		return this.x * vec.x + this.y * vec.y;
	}

	public Vector2l scale(double scale) {
		this.x *= scale;
		this.y *= scale;
		return this;
	}

	public Vector2l add(Vector2l vec) {
		this.x += vec.x;
		this.y += vec.y;
		return this;
	}

	public Vector2l add(long x, long y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2l sub(Vector2l vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		return this;
	}

	public Vector2l sub(long x, long y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	@Override
	public Vector2i toVector2i() {
		return new Vector2i((int) this.x, (int) this.y);
	}

	@Override
	public Vector2i toVector2i(Vector2i vec) {
		return vec.set((int) this.x, (int) this.y);
	}

	@Override
	public Vector2l toVector2l() {
		return this;
	}

	@Override
	public Vector2l toVector2l(Vector2l vec) {
		return vec.set(this.x, this.y);
	}

	@Override
	public Vector2f toVector2f() {
		return new Vector2f(this.x, this.y);
	}

	@Override
	public Vector2f toVector2f(Vector2f vec) {
		return vec.set(this.x, this.y);
	}

	@Override
	public Vector2d toVector2d() {
		return new Vector2d(this.x, this.y);
	}

	@Override
	public Vector2d toVector2d(Vector2d vec) {
		return vec.set(this.x, this.y);
	}

	@Override
	public Vector2l clone() {
		return new Vector2l(this);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Vector2l && equals((Vector2l) obj);
	}

	public boolean equals(Vector2l vec) {
		return this.x == vec.x && this.y == vec.y;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s]", this.x, this.y);
	}
}
