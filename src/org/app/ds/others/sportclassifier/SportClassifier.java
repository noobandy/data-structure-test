/**
 * @filenameName:org.app.ds.others.sportclassifier.Classifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 2:57:56 PM
 * @version: TODO
 */
package org.app.ds.others.sportclassifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import org.app.ds.others.classifier.Classifier;
import org.app.ds.others.classifier.IClassifier;

/**
 * @className:org.app.ds.others.sportclassifier.Classifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 2:57:56 PM
 */
public class SportClassifier {

    private IClassifier classifier;

    /**
     * 
     */
    public SportClassifier() {
        super();

    }

    public void initialize(String trainingDataFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(
                trainingDataFile));

        classifier = new Classifier(true);

        String line = null;
        boolean first = true;
        while ((line = reader.readLine()) != null) {
            if (first) {
                // ignore
                first = false;
            }
            else {
                String[] tokens = line.split(Pattern.quote("\t"));

                classifier.trainClassifier(
                        tokens[1],
                        new double[] { Double.parseDouble(tokens[2]),
                                Double.parseDouble(tokens[3]) });

            }
        }
        reader.close();
    }

    public void test(String testDataFile, OutputStream os) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(testDataFile));

        String line = null;
        PrintWriter writer = new PrintWriter(os);

        int totalPredictions = 0;
        int positivePredictions = 0;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(Pattern.quote("\t"));

            String name = tokens[0];

            String sport = tokens[1];
            double height = Double.parseDouble(tokens[2]);
            double weight = Double.parseDouble(tokens[3]);

            totalPredictions++;

            String predictedClass = classify(height, weight);

            boolean positive = predictedClass.equals(sport) ? true : false;
            if (positive) {
                positivePredictions++;
                writer.println("+ " + predictedClass + " " + name + " " + sport
                        + " " + height + " " + weight);
            }
            else {
                writer.println("- " + predictedClass + " " + name + " " + sport
                        + " " + height + " " + weight);
            }

        }
        reader.close();

        double percentageSuccess = ((double) positivePredictions / totalPredictions) * 100;
        writer.println(percentageSuccess + " %");
        writer.flush();
    }

    public String classify(double height, double weight) {

        return classifier.classify(new double[] { height, weight });
    }

    public static void main(String[] args) throws IOException {
        SportClassifier classifier = new SportClassifier();
        classifier
                .initialize("E:\\Downloads\\Guide To Data Mining Codes\\athletesTrainingSet.txt");
        System.out.println(classifier.classify(70, 140));

        classifier
                .test(
                        "E:\\Downloads\\Guide To Data Mining Codes\\athletesTestSet.txt",
                        System.out);
    }
}
