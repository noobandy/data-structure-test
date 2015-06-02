/**
 * 
 */
package org.app.ds.list;

import java.util.Iterator;

/**
 * @author anandm
 * 
 */
public class MyList<T> implements IList<T> {

	private static final int DEFAULT_CAPACITY = 32;

	private T[] data;

	private int size;

	private int capacity;

	private int current;

	/**
	 * 
	 */
	public MyList() {
		this(DEFAULT_CAPACITY);

	}

	/**
	 * @param capacity
	 */
	public MyList(int capacity) {
		super();
		this.capacity = capacity;
		data = (T[]) new Object[this.capacity];
		size = 0;
		current = 0;
	}

	private boolean ensureSpace(int index) {

		if (!(capacity > index)) {
			T[] temp = null;
			if (index - capacity < DEFAULT_CAPACITY) {
				temp = (T[]) new Object[capacity + DEFAULT_CAPACITY];
			} else {
				temp = (T[]) new Object[capacity + (index - capacity)];
			}

			for (int i = 0; i < data.length; i++) {
				temp[i] = data[i];
			}
			data = temp;

		}

		return true;
	}

	@Override
	public void add(T element) {
		if (ensureSpace(size + 1)) {
			data[current++] = element;
			size++;
		}
	}

	@Override
	public void add(int index, T element) {
		if (ensureSpace(index)) {
			data[index] = element;
			if (index > current) {
				size++;
			}
		}

	}

	@Override
	public void remove(T element) {
		int index = indexOf(element);
		if(index != -1) {
			data[index] = null;
		}
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		for (int i = 0; i <= data.length; i++) {
			if (data[i].equals(element)) {
				index = i;
				break;
			}
		}
		return index;
	}

	@Override
	public boolean addBefore(T before, T element) {
		

		return false;
	}

	@Override
	public boolean addAfter(T after, T element) {

		return false;
	}

	@Override
	public Iterator<T> iterator() {

		return null;
	}

	@Override
	public int size() {

		return size;
	}

}
