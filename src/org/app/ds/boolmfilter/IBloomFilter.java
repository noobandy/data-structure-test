/**
 * @filenameName:org.app.ds.boolmfilter.IBloomFilter.java
 * @description:TODO
 * @author anandm
 * @date Aug 31, 2015 4:32:45 PM
 * @version: TODO
 */
package org.app.ds.boolmfilter;

import java.util.Collection;

/**
 * @className:org.app.ds.boolmfilter.IBloomFilter.java
 * @description:TODO
 * @author anandm
 * @date Aug 31, 2015 4:32:45 PM
 */
public interface IBloomFilter<E> {

    void add(E element);

    void addAll(Collection<? extends E> elements);

    boolean contains(E element);

    boolean containsAll(Collection<? extends E> elements);
}
