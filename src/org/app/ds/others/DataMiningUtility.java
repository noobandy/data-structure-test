/**
 * 
 */
package org.app.ds.others;

import java.util.Arrays;

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

        double numerator = xySum - ((xSum * ySum) / n);
        double denominator = Math.sqrt(xSquareSum - (Math.pow(xSum, 2) / n))
                * Math.sqrt(ySquareSum - (Math.pow(ySum, 2) / n));

        double cofficient = 0;

        if ((numerator > 0 || numerator < 0)
                && (denominator > 0 || denominator < 0)) {
            cofficient = numerator / denominator;
        }

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

    public static final double adjustedCosineSimilarityCoefficient(double[] uRI,
                                                                   double[] uRJ,
                                                                   double[] uMean) {

        double n = uRI.length;

        double numerator = 0;
        double denominator = 0;

        double denominatorPartISquareSum = 0;
        double denominatorPartJSquareSum = 0;

        for (int i = 0; i < n; i++) {
            double numeratorPartI = uRI[i] - uMean[i];
            double numeratorPartJ = uRJ[i] - uMean[i];

            numerator = numerator + (numeratorPartI * numeratorPartJ);

            denominatorPartISquareSum = denominatorPartISquareSum
                    + Math.pow(numeratorPartI, 2);

            denominatorPartJSquareSum = denominatorPartJSquareSum
                    + Math.pow(numeratorPartJ, 2);

        }

        denominator = Math.sqrt(denominatorPartISquareSum)
                * Math.sqrt(denominatorPartJSquareSum);

        double cofficient = numerator / denominator;

        return cofficient;

    }

    public static final double normalize(double value, double min, double max) {

        double normalized = ((2 * (value - min)) - (max - min)) / (max - min);

        return normalized;
    }

    public static final double deNormalize(double normalized,
                                           double min,
                                           double max) {

        double value = (((normalized + 1) * (max - min)) / 2) + min;

        return value;
    }

    public static final double projectedSimilarityRating(double[] similarities,
                                                         double[] ratings) {

        double numerator = 0;
        double denominator = 0;

        for (int i = 0; i < ratings.length; i++) {
            numerator = numerator + (similarities[i] * ratings[i]);
            denominator = denominator + Math.abs(similarities[i]);
        }

        double projectedRating = numerator / denominator;

        return projectedRating;
    }

    public static final double mean(double[] values) {

        double sum = 0.0;

        for (double value : values) {
            sum = sum + value;
        }

        double mean = (sum / values.length);

        return mean;
    }

    public static final double standardDeviation(double[] values, double mean) {
        double dSquareSum = 0.0;

        for (double value : values) {
            dSquareSum = (dSquareSum + Math.pow((value - mean), 2));
        }

        double sd = Math.sqrt(dSquareSum / values.length);

        return sd;
    }

    public static final double[] standardScore(double[] values,
                                               double mean,
                                               double standardDeviation) {
        double standardScores[] = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            standardScores[i] = ((values[i] - mean) / standardDeviation);
        }

        return standardScores;

    }

    public static final double median(double[] values) {
        double[] internalCopy = Arrays.copyOf(values, values.length);
        Arrays.sort(internalCopy);
        double median;
        int middle = internalCopy.length / 2;
        if (internalCopy.length % 2 == 0) { // even
            median = (internalCopy[middle - 1] + internalCopy[middle]) / 2;
        }
        else { // odd
            median = internalCopy[middle];
        }

        return median;
    }

    public static final double absoluteStandardDeviation(double[] values,
                                                         double median) {
        double dSum = 0.0;

        for (double value : values) {
            dSum = dSum + Math.abs(value - median);
        }

        double asd = dSum / values.length;

        return asd;
    }

    public static final double[] modifiedStandardScore(double[] values,
                                                       double median,
                                                       double absoluteStandardDeviation) {
        double standardScores[] = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            standardScores[i] = ((values[i] - median) / absoluteStandardDeviation);
        }

        return standardScores;

    }

    public static void main(String[] args) {
        System.out.println(DataMiningUtility.pearsonCorrelationCoefficient(
                new double[] { 3.5, 2, 5, 1.5, 2 }, new double[] { 2, 3.5, 2,
                        3.5, 3 }));

        System.out.println(DataMiningUtility.cosineSimilarityCoefficient(
                new double[] { 3.5, 2, 0, 4.5, 5, 1.5, 2.5, 2 }, new double[] {
                        3, 0, 0, 5, 4, 2.5, 3, 0 }));

        System.out.println(DataMiningUtility
                .adjustedCosineSimilarityCoefficient(
                        new double[] { 4, 4, 5 }, new double[] { 1, 1, 3 },
                        new double[] { 2.75, 3.2, 4.25 }));

        double[] values = { 75000, 55000, 45000, 115000, 70000, 105000, 69000,
                43000 };
        double median = DataMiningUtility.median(values);
        double asd = DataMiningUtility
                .absoluteStandardDeviation(values, median);

        System.out.println(Arrays.toString(DataMiningUtility
                .modifiedStandardScore(values, median, asd)));

        double[] playCounts = { 21, 15, 12, 3, 7 };
        double medianPlayCounts = DataMiningUtility.median(playCounts);
        double asdPlayCounts = DataMiningUtility.absoluteStandardDeviation(
                playCounts, medianPlayCounts);

        System.out.println(Arrays.toString(DataMiningUtility
                .modifiedStandardScore(
                        playCounts, medianPlayCounts, asdPlayCounts)));
    }
}
