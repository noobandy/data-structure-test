/**
 * @filenameName:org.app.ds.collections.Test.java
 * @description:TODO
 * @author anandm
 * @date Aug 10, 2015 10:49:43 AM
 * @version: TODO
 */
package org.app.ds.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @className:org.app.ds.collections.Test.java
 * @description:TODO
 * @author anandm
 * @date Aug 10, 2015 10:49:43 AM
 */
public class Test {

    public static final <E> Set<E> findDupes(Set<E> data,
                                             Comparator<E> comparator) {
        Set<E> uniques = new HashSet<E>();
        Set<E> dupes = new TreeSet<E>(comparator);

        for (E obj : data) {
            if (!uniques.add(obj)) {
                dupes.add(obj);
            }
        }

        return dupes;

    }

    public static final List<String> trim(List<String> strings) {

        ListIterator<String> listIterator = strings.listIterator(0);
        while (listIterator.hasNext()) {
            listIterator.set(listIterator.next().trim());
        }
        return strings;
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>();

        strings.add("string 1");
        strings.add(" string 3 ");
        strings.add(" string 3 ");

        trim(strings);

        Integer[] integers = new Integer[3];

        Object object = new Object() {

        };

        System.out.println(object.hashCode());
        integers = strings.toArray(integers);

        System.out.println(integers[0]);

        List<String> argList = Arrays.asList(args);
        Collections.shuffle(argList);

    }

    public class MyCollection<E> implements Collection<E> {

        private static final int DEAFULT_SIZE = 10;

        private E[] data;

        private int size;

        /**
         * @param size
         */
        @SuppressWarnings("unchecked")
        public MyCollection(int size) {
            super();
            this.data = (E[]) new Object[size];
        }

        /**
         * 
         */
        public MyCollection() {
            this(DEAFULT_SIZE);

        }

        @Override
        public int size() {

            return size;
        }

        @Override
        public boolean isEmpty() {

            return size == 0;
        }

        @Override
        public boolean contains(Object o) {
            Iterator<E> iterator = iterator();

            while (iterator.hasNext()) {
                if (iterator.next().equals(o)) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public Iterator<E> iterator() {

            return null;
        }

        @Override
        public Object[] toArray() {

            return Arrays.copyOf(data, size + 1);
        }

        @Override
        public <T> T[] toArray(T[] a) {

            return (T[]) Arrays.copyOf(data, size + 1);
        }

        @Override
        public boolean add(E e) {
            if (size < data.length) {

                data[size++] = e;

                return true;
            }
            else {
                throw new CollectionIsFullException("collection is full");
            }

        }

        @Override
        public boolean remove(Object o) {
            Iterator<E> iterator = iterator();

            while (iterator.hasNext()) {
                if (iterator.next().equals(o)) {
                    iterator.remove();
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            Collection<?> c1;
            Collection<?> c2;
            if (size() < c.size()) {
                c1 = this;
                c2 = c;
            }
            else {
                c1 = c;
                c2 = this;
            }

            for (Object obj : c2) {
                if (!c1.contains(obj)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            if (size + c.size() < data.length) {
                for (E obj : c) {
                    add(obj);
                }
                return true;
            }
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            boolean modified = false;

            for (Object obj : c) {
                if (remove(obj) && !modified) {
                    modified = true;
                }
            }
            return modified;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            boolean modified = false;

            Iterator<E> iterator = iterator();
            while (iterator.hasNext()) {
                if (!c.contains(iterator.next())) {
                    if (!modified) {
                        modified = true;
                    }

                    iterator.remove();
                }
            }
            return modified;
        }

        @Override
        public void clear() {
            size = 0;
            data = (E[]) new Object[data.length];
        }

    }
}
