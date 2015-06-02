/**
 * 
 */
package org.app.ds.queue;

import java.util.Random;

/**
 * @author anandm
 * 
 */
public class CircularPriorityQueue<T extends Comparable<T>> implements
		IQueue<T> {

	private static final int DEFAULT_CAPACITY = 10;
	private Object[] data;
	private int capacity;
	private int front;
	private int rear;

	/**
	 * 
	 */
	public CircularPriorityQueue() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * 
	 */
	public CircularPriorityQueue(int capacity) {
		super();
		this.capacity = capacity;
		data = new Object[this.capacity];
		clear();
	}

	@Override
	public boolean isEmpty() {

		return front == rear;
	}

	@Override
	public T getFront() {

		if (isEmpty()) {
			throw new IllegalStateException("Queue is empty");
		}

		return (T) data[front];

	}

	@Override
	public T dequeue() {

		if (isEmpty()) {
			throw new IllegalStateException("Queue is empty");
		}

		T item = (T) data[front];

		data[front] = null;

		front = (front + 1) % capacity;

		return item;
	}

	@Override
	public void enqueue(T e) {

		if (((rear + 1) % capacity) == front) {
			throw new IllegalStateException("Queue is full");
		}

		if (isEmpty()) {
			data[rear] = e;
		} else {

		}

		rear = (rear + 1) % capacity;

	}

	@Override
	public void clear() {
		front = rear = 0;
	}

	public static void main(String[] args) {
		CircularPriorityQueue<Integer> queue = new CircularPriorityQueue(5);
		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			queue.enqueue(random.nextInt() % 4);
		}

		while (!queue.isEmpty()) {
			System.out.println(queue.dequeue());
		}
	}
}
