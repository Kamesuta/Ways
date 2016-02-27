package com.kamesuta.mc.ways.renderer;

import java.util.List;

import com.kamesuta.mc.ways.util.vector.Vector3f;

/**
 * 描画パスを演算するアルゴリズム群です。
 *
 * @author Kamesuta
 */
public enum RenderingPath {
	Cave {
		@Override
		public void pathAll(List<Vector3f> update, RenderingBuffer source, float approach) {
			for (Vector3f a : update) {
				path(a, source, approach);
			}
		}

		@Override
		public void path(Vector3f update, RenderingBuffer source, float approach) {
			Vector3f last = source.get(source.size()-1);
			for (int i = 0; i < source.size(); i++) {
				Vector3f b = source.get(i);
				if (update.lengthSquaredTo(b) < approach) {
					if (b != last)
						source.add(b);
					return;
				}
			}
			source.add(update);
		}
	};

	public abstract void pathAll(List<Vector3f> update, RenderingBuffer source, float approach);

	public abstract void path(Vector3f update, RenderingBuffer source, float approach);
}
