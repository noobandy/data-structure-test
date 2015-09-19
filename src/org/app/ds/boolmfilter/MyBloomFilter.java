/**
 * @filenameName:org.app.ds.boolmfilter.MyBloomFilter.java
 * @description:TODO
 * @author anandm
 * @date Aug 31, 2015 4:34:21 PM
 * @version: TODO
 */
package org.app.ds.boolmfilter;

import java.util.BitSet;
import java.util.Collection;

/**
 * @className:org.app.ds.boolmfilter.MyBloomFilter.java
 * @description:TODO
 * @author anandm
 * @date Aug 31, 2015 4:34:21 PM
 */
public class MyBloomFilter<E> implements IBloomFilter<E> {

    private BitSet bitSet;

    private int bitSetSize;

    private int noOfHashes;

    /**
     * 
     * @param filterSize
     * @param noOfHashes
     */
    public MyBloomFilter(int filterSize, int noOfHashes) {
        super();

        bitSetSize = filterSize;
        this.noOfHashes = noOfHashes;
        this.bitSet = new BitSet(bitSetSize);
    }

    @Override
    public void add(E element) {
        int[] hashes = hashes(element.toString().getBytes(), noOfHashes);

        for (int hash : hashes) {
            bitSet.set(hash);
        }

    }

    private int[] hashes(byte[] bytes, int noOfHashes) {
        int[] hashes = new int[noOfHashes];
        for (int i = 0; i < noOfHashes; i++) {
            hashes[i] = MurmurHash.hash32(bytes, bytes.length, i) % bitSetSize;

        }
        return hashes;
    }

    public void addAll(Collection<? extends E> elements) {
        for (E e : elements) {
            add(e);
        }
    }

    @Override
    public boolean contains(E element) {
        int[] hashes = hashes(element.toString().getBytes(), noOfHashes);
        for (int hash : hashes) {
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<? extends E> elements) {

        for (E e : elements) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

}
