/**
 * 
 */
package org.app.ds.applications;

import java.util.Scanner;

/**
 * @author anandm
 * 
 */
public class IOTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(",");

		int totalNumbers = 0;
		double sumOfNumbers = 0;
		double average = 0;
		while (scanner.hasNext()) {
			double nextNumber = scanner.nextDouble();
			sumOfNumbers += nextNumber;
		}

		if (totalNumbers > 0) {
			average = sumOfNumbers / totalNumbers;
		}

		System.out.println("Average = " + average);

		scanner.close();
	}

}
