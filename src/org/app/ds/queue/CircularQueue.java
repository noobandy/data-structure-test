/**
 * 
 */
package org.app.ds.queue;

/**
 * @author anandm
 * 
 */
public class CircularQueue<T> implements IQueue<T> {

	private static final int DEFAULT_CAPACITY = 10;
	private Object[] data;
	private int capacity;
	private int front;
	private int rear;

	/**
	 * 
	 */
	public CircularQueue() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * 
	 */
	public CircularQueue(int capacity) {
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

		data[rear] = e;

		rear = (rear + 1) % capacity;

	}

	@Override
	public void clear() {
		front = rear = 0;
	}

	public static void main(String[] args) {
		CircularQueue<Integer> queue = new CircularQueue<Integer>(5);

		System.out.println("Empty : " + queue.isEmpty());

		try {
			queue.getFront();
		} catch (IllegalStateException e) {
			System.out.println("Empty Exception: " + e.getMessage());
		}

		try {
			queue.dequeue();
		} catch (IllegalStateException e) {
			System.out.println("Empty Exception: " + e.getMessage());
		}

		for (int i = 0; i < 5; i++) {
			queue.enqueue(i);
		}

		System.out.println("Full : " + !queue.isEmpty());

		try {
			queue.enqueue(6);
		} catch (IllegalStateException e) {
			System.out.println("Full Exception: " + e.getMessage());
		}

		for (int i = 0; i < 5; i++) {
			System.out.println(queue.dequeue());
		}

		System.out.println("Empty : " + queue.isEmpty());

	}
}
