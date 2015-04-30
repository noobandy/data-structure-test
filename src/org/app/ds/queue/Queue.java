/**
 * 
 */
package org.app.ds.queue;

import java.util.LinkedList;

/**
 * @author anandm
 * 
 */
public class Queue<T> implements IQueue<T> {

	private LinkedList<T> data;

	public Queue() {
		super();
		data = new LinkedList<T>();
	}

	@Override
	public boolean isEmpty() {

		return data.isEmpty();
	}

	@Override
	public T getFront() {

		return data.getFirst();
	}

	@Override
	public T dequeue() {
		T element = data.getFirst();
		data.removeFirst();
		return element;
	}

	@Override
	public void enqueue(T e) {
		data.addLast(e);
	}

	@Override
	public void clear() {
		data.clear();
	}

}
