/**
 * 
 */
package org.app.ds.stack;

/**
 * @author anandm
 * 
 */
public interface IStack<T> {

	void push(T element);

	T pop();

	T peek();

	boolean isEmpty();

}
