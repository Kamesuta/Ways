package com.kamesuta.mc.ways.world.storage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Way implements Serializable {
	private final LinkedList<Node> ways;

	public Way(LinkedList<Node> ways)
	{
		this.ways = ways;
	}

	public Way(Collection<Node> ways)
	{
		this(new LinkedList<Node>(ways));
	}

	public Way(Node[] ways)
	{
		this(Arrays.asList(ways));
	}

	public Way()
	{
		this(new LinkedList<Node>());
	}

	public Node getFirst() {
		return ways.getFirst();
	}

	public void addFirst(Node e) {
		ways.addFirst(e);
	}

	public Node getLast() {
		return ways.getLast();
	}

	public void addLast(Node e) {
		ways.addLast(e);
	}

	public Iterator<Node> iterator() {
		return ways.iterator();
	}

	public void clear() {
		ways.clear();
	}

	public Node get(int index) {
		return ways.get(index);
	}

	public Node set(int index, Node element) {
		return ways.set(index, element);
	}

	public void add(int index, Node element) {
		ways.add(index, element);
	}

	public Node remove(int index) {
		return ways.remove(index);
	}

	public Node peekFirst() {
		return ways.peekFirst();
	}

	public Node peekLast() {
		return ways.peekLast();
	}

	public Node pollFirst() {
		return ways.pollFirst();
	}

	public Node pollLast() {
		return ways.pollLast();
	}

	public List<Node> subList(int fromIndex, int toIndex) {
		return ways.subList(fromIndex, toIndex);
	}
}
