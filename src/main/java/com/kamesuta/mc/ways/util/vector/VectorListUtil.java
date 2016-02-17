package com.kamesuta.mc.ways.util.vector;

import java.util.Collection;
import java.util.Iterator;

public class VectorListUtil {
	public static <E extends Collection<? extends Vector2>, T extends Collection<Vector2i>> T toVector2i(E list, T newlist) {
		for(Iterator<? extends Vector2> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector2i());
		}
		return newlist;
	}

	public static <E extends Collection<? extends Vector2>, T extends Collection<Vector2l>> T toVector2l(E list, T newlist) {
		for(Iterator<? extends Vector2> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector2l());
		}
		return newlist;
	}

	public static <E extends Collection<? extends Vector2>, T extends Collection<Vector2f>> T toVector2f(E list, T newlist) {
		for(Iterator<? extends Vector2> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector2f());
		}
		return newlist;
	}

	public static <E extends Collection<? extends Vector2>, T extends Collection<Vector2d>> T toVector2d(E list, T newlist) {
		for(Iterator<? extends Vector2> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector2d());
		}
		return newlist;
	}
	public static <E extends Collection<? extends Vector3>, T extends Collection<Vector3i>> T toVector3i(E list, T newlist) {
		for(Iterator<? extends Vector3> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector3i());
		}
		return newlist;
	}

	public static <E extends Collection<? extends Vector3>, T extends Collection<Vector3l>> T toVector3l(E list, T newlist) {
		for(Iterator<? extends Vector3> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector3l());
		}
		return newlist;
	}

	public static <E extends Collection<? extends Vector3>, T extends Collection<Vector3f>> T toVector3f(E list, T newlist) {
		for(Iterator<? extends Vector3> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector3f());
		}
		return newlist;
	}

	public static <E extends Collection<? extends Vector3>, T extends Collection<Vector3d>> T toVector3d(E list, T newlist) {
		for(Iterator<? extends Vector3> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector3d());
		}
		return newlist;
	}

	public static <E extends Collection<? extends Vector4>, T extends Collection<Vector4i>> T toVector4i(E list, T newlist) {
		for(Iterator<? extends Vector4> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector4i());
		}
		return newlist;
	}

	public static <E extends Collection<? extends Vector4>, T extends Collection<Vector4l>> T toVector4l(E list, T newlist) {
		for(Iterator<? extends Vector4> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector4l());
		}
		return newlist;
	}

	public static <E extends Collection<? extends Vector4>, T extends Collection<Vector4f>> T toVector4f(E list, T newlist) {
		for(Iterator<? extends Vector4> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector4f());
		}
		return newlist;
	}

	public static <E extends Collection<? extends Vector4>, T extends Collection<Vector4d>> T toVector4d(E list, T newlist) {
		for(Iterator<? extends Vector4> it = list.iterator(); it.hasNext();) {
			newlist.add(it.next().toVector4d());
		}
		return newlist;
	}
}
