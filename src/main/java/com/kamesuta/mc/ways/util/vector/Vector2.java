package com.kamesuta.mc.ways.util.vector;

import java.io.Serializable;

public interface Vector2 extends Cloneable, Serializable {
	public Vector2i toVector2i();

	public Vector2i toVector2i(Vector2i vec);

	public Vector2l toVector2l();

	public Vector2l toVector2l(Vector2l vec);

	public Vector2f toVector2f();

	public Vector2f toVector2f(Vector2f vec);

	public Vector2d toVector2d();

	public Vector2d toVector2d(Vector2d vec);
}
