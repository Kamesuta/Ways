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
		public void pathAll(List<Vector3f> source, RenderingBuffer update, float approach) {
			for (Vector3f a : source) {
				path(a, update, approach);
			}
		}

		@Override
		public void path(Vector3f source, RenderingBuffer update, float approach) {
			for (int i = 0; i < update.last(); i++) {
				Vector3f b = update.get(i);
				if (source.lengthSquaredTo(b) < approach) {
					if (0 <= i-1 && b != update.get(i-1))
						update.add(b);
					return;
				}
			}
			update.add(source);
		}

		@Override
		public void pointAll(List<Vector3f> source, RenderingBuffer update, float approach) {
			this.pathAll(source, update, approach);
		}

		@Override
		public void point(Vector3f source, RenderingBuffer update, float approach) {

		}
	};

	public abstract void pathAll(List<Vector3f> source, RenderingBuffer update, float approach);

	public abstract void path(Vector3f source, RenderingBuffer update, float approach);

	public abstract void pointAll(List<Vector3f> source, RenderingBuffer update, float approach);

	public abstract void point(Vector3f source, RenderingBuffer update, float approach);
}
