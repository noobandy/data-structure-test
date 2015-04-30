/**
 * 
 */
package org.app.ds.queue;

/**
 * @author anandm
 * 
 */
public class CircularQueue<T> implements IQueue<T> {

	private Object[] data;
	private int size;
	private int front;
	private int back;

	/**
	 * 
	 */
	public CircularQueue(int size) {
		super();

		data = new Object[size];
		front = -1;
		back = -1;
		this.size = size;

	}

	@Override
	public boolean isEmpty() {

		return front == -1 && back == -1;
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

		if (front == back) {
			front = -1;
			back = -1;
		} else {
			front++;
		}

		return item;
	}

	@Override
	public void enqueue(T e) {
		if ((front == 0 && back == (size - 1)) || (back + 1 == front)) {
			throw new IllegalStateException("Queue is full");
		}

		if (isEmpty()) {
			front++;
			back++;
		} else {
			back++;
		}

		data[back] = e;

	}

	@Override
	public void clear() {
		front = -1;
		back = -1;
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
