package com.kamesuta.mc.ways.renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.kamesuta.mc.ways.util.vector.Vector3f;
import com.kamesuta.mc.ways.util.vector.Vector3l;

/**
 * 描画パスを演算するアルゴリズム群です。
 * @author Kamesuta
 */
public enum RenderingPath {
	Cave {
		@Override
		public ArrayList<Vector3f> path(List<Vector3l> sourcelist, float approach)
		{
			ArrayList<Vector3f> out = new ArrayList<Vector3f>();
			ArrayList<Vector3l> source = new ArrayList<Vector3l>(sourcelist);
			A: for (ListIterator<Vector3l> ita = source.listIterator(); ita.hasNext();)
			{
				Vector3l a = ita.next();
				if(a != null)
				{
					Vector3f af = a.toVector3f();
					for (ListIterator<Vector3f> itout = out.listIterator(); itout.hasNext();)
					{
						Vector3f o = itout.next();
						if (af.lengthSquaredTo(o) <= approach + 1)
						{
							out.add(o);
							continue A;
						}
					}

					int ani = 1;
					for (ListIterator<Vector3l> itb = source.listIterator(); itb.hasNext();)
					{
						Vector3l b = itb.next();
						if(b != null)
						{
							if (a.lengthSquaredTo(b) < approach)
							{
								af.add(b.toVector3f());
								ani++;
								itb.set(null);
							}
						}
					}
					out.add(af.scale(1d/ani));
				}
			}
			return out;
		}

		@Override
		public ArrayList<Vector3f> point(List<Vector3l> sourcelist, float approach) {
			return null;
//			return sourcelist.
		}
	},
	;

	public abstract ArrayList<Vector3f> path(List<Vector3l> sourcelist, float approach);
	public abstract ArrayList<Vector3f> point(List<Vector3l> sourcelist, float approach);
}
