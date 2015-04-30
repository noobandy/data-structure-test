/**
 * 
 */
package org.app.ds.stack;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author anandm
 * 
 */
public class Stack<T> implements IStack<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LinkedList<T> data;

	/**
	 * 
	 */
	public Stack() {
		super();
		data = new LinkedList<T>();

	}

	@Override
	public void push(T element) {
		data.addFirst(element);
	}

	@Override
	public T pop() {
		T element = data.getFirst();
		data.removeFirst();
		return element;
	}

	@Override
	public boolean isEmpty() {

		return data.isEmpty();
	}

	@Override
	public T peek() {

		return data.getFirst();
	}

}
