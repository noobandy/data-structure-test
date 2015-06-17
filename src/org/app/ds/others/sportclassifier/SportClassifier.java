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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.app.ds.others.classifier.Classifier;
import org.app.ds.others.classifier.IClassifier;
import org.app.ds.others.classifier.IItem;

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

    private List<Player> players;

    /**
     * @className:org.app.ds.others.sportclassifier.Classifier.java
     * @description:TODO
     * @author anandm
     * @date Jun 16, 2015 3:30:24 PM
     */
    public class Player implements IItem {

        private String name;

        private String sport;

        private double height;

        private double weight;

        private double[] featureVector;

        /**
         * 
         */
        public Player() {
            super();

        }

        /**
         * @param name
         * @param sport
         * @param height
         * @param weight
         */
        public Player(String name, String sport, double height, double weight) {
            super();
            this.name = name;
            this.sport = sport;
            this.height = height;
            this.weight = weight;
            featureVector = new double[] { this.height, this.weight };
        }

        /**
         * @methodName:Classifier.java.getItemClass
         * @description:TODO
         * @author anandm
         * @return
         */
        @Override
        public String getItemClass() {

            return sport;
        }

        /**
         * @methodName:Classifier.java.getFeatureVector
         * @description:TODO
         * @author anandm
         * @return
         */
        @Override
        public double[] getFeatureVector() {

            return featureVector;
        }

    }

    public void initialize(String trainingDataFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(
                trainingDataFile));
        players = new ArrayList<Player>();

        String line = null;
        boolean first = true;
        while ((line = reader.readLine()) != null) {
            if (first) {
                // ignore
                first = false;
            }
            else {

                players.add(_toPlayer(line));
            }
        }
        reader.close();

        classifier = new Classifier(true);

        classifier.trainClassifier(players);
    }

    private Player _toPlayer(String line) {
        String[] tokens = line.split(Pattern.quote("\t"));

        return new Player(tokens[0], tokens[1], Double.parseDouble(tokens[2]),
                Double.parseDouble(tokens[3]));
    }

    public void test(String testDataFile, OutputStream os) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(testDataFile));

        List<Player> testPlayers = new ArrayList<SportClassifier.Player>();

        String line = null;
        while ((line = reader.readLine()) != null) {

            testPlayers.add(_toPlayer(line));
        }
        reader.close();

        PrintWriter writer = new PrintWriter(os);

        int positivePredictions = 0;

        for (Player player : testPlayers) {
            String predictedClass = classify(player.height, player.weight);

            boolean positive = predictedClass.equals(player.getItemClass()) ? true
                    : false;
            if (positive) {
                positivePredictions++;
                writer.println("+ " + predictedClass + " " + player.name + " "
                        + player.sport + " " + player.height + " "
                        + player.weight);
            }
            else {
                writer.println("- " + predictedClass + " " + player.name + " "
                        + player.sport + " " + player.height + " "
                        + player.weight);
            }
        }
        double percentageSuccess = ((double) positivePredictions / testPlayers
                .size()) * 100;
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
