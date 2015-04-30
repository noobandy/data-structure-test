/**
 * 
 */
package org.app.ds.queue;

/**
 * @author anandm
 * 
 */
public interface IQueue<T> {
	
	public boolean isEmpty();

	public T getFront();

	public T dequeue();

	public void enqueue(T e);

	public void clear();
}
