/**
 * 
 */
package org.app.ds.list;

import java.util.Iterator;

/**
 * @author anandm
 * 
 */
public interface IList<T> {

	void add(T element);

	void add(int index, T element);

	void remove(T element);

	int indexOf(T element);

	boolean addBefore(T before, T element);

	boolean addAfter(T after, T element);

	Iterator<T> iterator();
	
	int size();
}
