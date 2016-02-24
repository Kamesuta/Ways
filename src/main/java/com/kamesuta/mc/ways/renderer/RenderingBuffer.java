package com.kamesuta.mc.ways.renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.io.Closeable;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

import com.kamesuta.mc.ways.util.vector.Vector3f;

public class RenderingBuffer implements Closeable {
	public static final int capacity_chunk = 1000;

	public static final int BYTES_PER_FLOAT = 4; // Float byte size
	public static final int vertex_size = 3; // X, Y, Z,

	private ArrayList<Vector3f> array_cache = new ArrayList<Vector3f>();
	private final int vbo_vertex_handle;
	private final FloatBuffer vertex_data = BufferUtils.createFloatBuffer(vertex_size);
	private int capacity = capacity_chunk;
	private int last_pos = 0;

	public RenderingBuffer() {
		this.vbo_vertex_handle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo_vertex_handle);
		glBufferData(GL_ARRAY_BUFFER, capacity, GL_DYNAMIC_DRAW);
//		glBufferSubData(GL_ARRAY_BUFFER, 0, vertex_data);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public int handle() {
		return vbo_vertex_handle;
	}

	public int last() {
		return last_pos;
	}

	public Vector3f get(int pos) {
		return array_cache.get(pos);
	}

	public void render(int mode) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo_vertex_handle);
		glVertexPointer(vertex_size, GL_FLOAT, 0, 0l);
		glDrawArrays(mode, 0, vertex_size * last_pos);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public RenderingBuffer add(Vector3f vec) {
		last_pos++;
		array_cache.add(vec);
		set(last_pos, toBuffer(vec));
		return this;
	}

	public RenderingBuffer set(int pos, Vector3f vec) {
		array_cache.set(pos, vec);
		set(pos, toBuffer(vec));
		return this;
	}

	protected FloatBuffer toBuffer(Vector3f vec) {
		vertex_data.rewind();
		vertex_data.put(vec.x).put(vec.y).put(vec.z);
		vertex_data.flip();
		return vertex_data;
	}

	protected void set(int pos, FloatBuffer buf) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo_vertex_handle);
		if (last_pos > capacity) {
			capacity += capacity_chunk;
			glBufferData(GL_ARRAY_BUFFER, capacity, GL_DYNAMIC_DRAW);
		}
		glBufferSubData(GL_ARRAY_BUFFER, BYTES_PER_FLOAT * vertex_size * pos, buf);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	@Override
	public void close() {
		glDeleteBuffers(vbo_vertex_handle);
	}
}
