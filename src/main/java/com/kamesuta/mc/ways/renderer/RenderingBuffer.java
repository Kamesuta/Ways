package com.kamesuta.mc.ways.renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;

import org.lwjgl.BufferUtils;

import com.kamesuta.mc.ways.reference.Reference;
import com.kamesuta.mc.ways.util.vector.Vector3f;

public class RenderingBuffer implements Closeable {
	public static final int capacity_chunk = 10;

	public static final int BYTES_PER_FLOAT = 4; // Float byte size
	public static final int vertex_size = 3; // X, Y, Z,

	private final int vbo_vertex_handle;
	private final FloatBuffer vertex_data = BufferUtils.createFloatBuffer(vertex_size);
	private int capacity = capacity_chunk;
	private int last_pos = 0;
	private Vector3f[] array_cache = new Vector3f[capacity];

	public RenderingBuffer() {
		this.vbo_vertex_handle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo_vertex_handle);
		glBufferData(GL_ARRAY_BUFFER, BYTES_PER_FLOAT * vertex_size * capacity, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public int handle() {
		return vbo_vertex_handle;
	}

	public int size() {
		return last_pos;
	}

	public Vector3f get(int pos) {
		if (0 <= pos && pos < last_pos)
			return array_cache[pos];
		else
			return null;
	}

	public void render(int mode) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo_vertex_handle);
		glVertexPointer(vertex_size, GL_FLOAT, 0, 0l);
		glDrawArrays(mode, 0, last_pos);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public RenderingBuffer add(Vector3f vec) {
		extend(last_pos);
		setGL(last_pos, toBuffer(vec));
		array_cache[last_pos] = vec;
		last_pos++;
		Reference.logger.info(last_pos + ":" + capacity);
		return this;
	}

/*	public RenderingBuffer add(Collection<Vector3f> veclist) {
		for (Vector3f vec : veclist) {
			add(vec);
		}
		return this;
	}
*/
/*	public RenderingBuffer set(int pos, Vector3f vec) {
		setGL(pos, toBuffer(vec));
		array_cache[pos] = vec;
		return this;
	}
*/
	private FloatBuffer toBuffer(Vector3f vec) {
		vertex_data.rewind();
		vertex_data.put(vec.x).put(vec.y).put(vec.z);
		vertex_data.flip();
		return vertex_data;
	}

	private void setGL(int pos, FloatBuffer buf) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo_vertex_handle);
		glBufferSubData(GL_ARRAY_BUFFER, BYTES_PER_FLOAT * vertex_size * pos, buf);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	private void extend(int size) {
		if (size >= capacity) {
			capacity += capacity_chunk;
			// Array Extend
			{
				array_cache = Arrays.copyOf(array_cache, capacity);
			}
			// GL Extend
			{
				glBindBuffer(GL_ARRAY_BUFFER, vbo_vertex_handle);
				ByteBuffer buffer = glMapBuffer(GL_ARRAY_BUFFER, GL_READ_ONLY, null);
				glBufferData(GL_ARRAY_BUFFER, BYTES_PER_FLOAT * vertex_size * capacity, GL_DYNAMIC_DRAW);
				glBufferSubData(GL_ARRAY_BUFFER, 0, buffer);
				glBindBuffer(GL_ARRAY_BUFFER, 0);
			}
		}
	}

	@Override
	public void close() {
		glDeleteBuffers(vbo_vertex_handle);
	}
}
