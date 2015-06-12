package org.app.ds.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Utils {

	public interface IPredicate<T> {
		boolean matches(T element);
	}

	public static final <T> T getT(T a1, T a2) {
		return a2;
	}

	public static final Object getSecondT(List<? super Number> arg) {

		return arg.get(0);

	}

	public static final <T> int countMatching(Collection<T> collection,
			IPredicate<T> predicate) {
		int count = 0;
		for (T t : collection) {
			if (predicate.matches(t)) {
				count++;
			}
		}
		return count;
	}

	public static final <T> void swap(T[] data, int pos1, int pos2) {
		T temp = data[pos1];
		data[pos1] = data[pos2];
		data[pos1] = temp;
	}

	public static final <T extends Comparable<T>> T max(T[] data, int beg,
			int end) {

		T max = null;

		for (int i = beg; i < end; i++) {
			T t = data[i];
			if (max == null) {
				max = t;
			} else {
				if (max.compareTo(t) < 0) {
					max = t;
				}
			}
		}
		return max;
	}

	public static final <T extends Comparable<T>> void swapFirst(
			List<T> listOne, List<T> listTwo) {
		T temp = listOne.get(0);
		listOne.set(0, listTwo.get(0));
		listTwo.set(0, temp);
	}

	public static void main(String[] args) {
		Utils.getT("anand", 1);

		//Utils.getSecondT(new ArrayList<Object>());

		List<Number> ln = new ArrayList<Number>();

		List<Integer> li = new ArrayList<Integer>();

		// ln = li;

		System.out.println(Utils.max(new Integer[] { 50, 30, 70, 20, 40, 90 }, 0, 3));
		
		List<A> list = new ArrayList<A>();
		list.add(new B());
		Process process = new Process();
		process.process(list);
		
		List<B> bs = new ArrayList<B>();
		
		bs.add(new B());
		
		process.process(bs);
	}
}

class A {
	
}

class B extends A {
	
}

class C extends A {
	
}

class Process {
	
	public void process(List<? extends A> list) {
		
	}
}
