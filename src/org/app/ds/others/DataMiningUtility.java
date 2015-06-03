/**
 * 
 */
package org.app.ds.others;

/**
 * @author anandm
 * 
 */
public final class DataMiningUtility {

	/**
	 * 
	 */
	private DataMiningUtility() {
		super();

	}

	private static final double minkowskiDistance(double[] x, double[] y, int r) {
		double distance = 0;

		for (int i = 0; i < x.length; i++) {
			distance = distance + Math.pow(Math.abs(x[i] - y[i]), r);
		}

		distance = Math.pow(distance, 1 / r);

		return distance;
	}

	/**
	 * Use when data is dense
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static final double manhattanDistance(double[] x, double[] y) {
		return minkowskiDistance(x, y, 1);
	}

	public static final double ecludianDistance(double[] x, double[] y) {
		return minkowskiDistance(x, y, 2);
	}

	/**
	 * Use when data suffers from grade inflation
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static final double pearsonCorrelationCoefficient(double[] x,
			double[] y) {

		double xySum = 0;
		double xSum = 0;
		double ySum = 0;
		double xSquareSum = 0;
		double ySquareSum = 0;
		int n = x.length;

		for (int i = 0; i < n; i++) {
			xySum = xySum + (x[i] * y[i]);

			xSum = xSum + x[i];

			ySum = ySum + y[i];

			xSquareSum = xSquareSum + Math.pow(x[i], 2);

			ySquareSum = ySquareSum + Math.pow(y[i], 2);

		}

		double cofficient = (xySum - ((xSum * ySum) / n))
				/ (Math.sqrt(xSquareSum - (Math.pow(xSum, 2) / n)) * Math
						.sqrt(ySquareSum - (Math.pow(ySum, 2) / n)));

		return cofficient;

	}

	/**
	 * Use for sparse data
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static final double cosineSimilarityCoefficient(double[] x,
			double[] y) {

		double xySum = 0;

		double xSquareSum = 0;
		double ySquareSum = 0;
		int n = x.length;

		for (int i = 0; i < n; i++) {
			xySum = xySum + (x[i] * y[i]);

			xSquareSum = xSquareSum + Math.pow(x[i], 2);

			ySquareSum = ySquareSum + Math.pow(y[i], 2);

		}

		double cofficient = xySum
				/ (Math.sqrt(xSquareSum) * Math.sqrt(ySquareSum));

		return cofficient;

	}

	public static void main(String[] args) {
		System.out.println(DataMiningUtility.pearsonCorrelationCoefficient(
				new double[] { 3.5, 2, 5, 1.5, 2 }, new double[] { 2, 3.5, 2,
						3.5, 3 }));

		System.out.println(DataMiningUtility.cosineSimilarityCoefficient(
				new double[] { 3.5, 2, 0, 4.5, 5, 1.5, 2.5, 2 }, new double[] {
						3, 0, 0, 5, 4, 2.5, 3, 0 }));
	}

}
