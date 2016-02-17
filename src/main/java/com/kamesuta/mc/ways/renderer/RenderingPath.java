package com.kamesuta.mc.ways.renderer;

import java.util.ArrayList;
import java.util.List;

import com.kamesuta.mc.ways.util.vector.Vector3f;

/**
 * 描画パスを演算するアルゴリズム群です。
 * @author Kamesuta
 */
public enum RenderingPath {
	Cave {
		@Override
		public List<Vector3f> path(List<Vector3f> source, float approach)
		{
			ArrayList<Vector3f> out = new ArrayList<Vector3f>();
			Object o = null;
			A: for (Vector3f a : source)
			{
				Vector3f af = new Vector3f(a);
				int ani = 1;
				for (Vector3f b : out)
				{
					if (a.lengthSquaredTo(b) < approach)
					{
						af.add(b);
						ani++;
						if(b == o) out.add(b);
						o = b;
						continue A;
					}
				}
				out.add(af.scale(1d/ani));
			}
			return out;
		}

		@Override
		public List<Vector3f> point(List<Vector3f> sourcelist, float approach) {
			return sourcelist;
		}
	};

	public abstract List<Vector3f> path(List<Vector3f> sourcelist, float approach);
	public abstract List<Vector3f> point(List<Vector3f> sourcelist, float approach);
}
