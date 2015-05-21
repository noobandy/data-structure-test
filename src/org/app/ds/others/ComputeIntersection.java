/**
 * 
 */
package org.app.ds.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anandm
 * 
 */
public class ComputeIntersection {

	/**
	 * 
	 */
	public ComputeIntersection() {
		super();
	}

	public Integer[] intersect(int[] setOne, int[] setTwo) {
		List<Integer> intersection = new ArrayList<Integer>();
		Arrays.sort(setOne);
		Arrays.sort(setTwo);

		for (int i = 0, j = 0; i < setOne.length && j < setTwo.length;) {
			if (setOne[i] < setTwo[j]) {
				i++;
			} else if (setOne[i] > setTwo[j]) {
				j++;
			} else {
				intersection.add(setOne[i]);
				i++;
				j++;
			}
		}

		Integer[] ints = new Integer[intersection.size()];

		return intersection.toArray(ints);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] setOne = new int[] { 2, 1, 10, 8, 9, 6 };
		int[] setTwo = new int[] { 8, 9, 2 };

		ComputeIntersection computeIntersection = new ComputeIntersection();

		Integer[] subset = computeIntersection.intersect(setOne, setTwo);
		for (Integer integer : subset) {
			System.out.println(integer);
		}

	}

}
