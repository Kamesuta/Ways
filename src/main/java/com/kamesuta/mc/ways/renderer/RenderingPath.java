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
			for (int i = 0; i < source.size(); i++) {
				Vector3f b = source.get(i);
				if (update.lengthSquaredTo(b) < approach) {
					if (0 <= i-1 && b != source.get(i-1))
						source.add(b);
					return;
				}
			}
			source.add(update);
		}

		@Override
		public void pointAll(List<Vector3f> update, RenderingBuffer source, float approach) {
			this.pathAll(update, source, approach);
		}

		@Override
		public void point(Vector3f update, RenderingBuffer source, float approach) {

		}
	};

	public abstract void pathAll(List<Vector3f> update, RenderingBuffer source, float approach);

	public abstract void path(Vector3f update, RenderingBuffer source, float approach);

	public abstract void pointAll(List<Vector3f> update, RenderingBuffer source, float approach);

	public abstract void point(Vector3f update, RenderingBuffer source, float approach);
}
