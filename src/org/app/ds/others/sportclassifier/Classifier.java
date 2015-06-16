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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.app.ds.others.DataMiningUtility;

/**
 * @className:org.app.ds.others.sportclassifier.Classifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 2:57:56 PM
 */
public class Classifier {

    private List<Player> players = new ArrayList<Player>();

    /**
     * @className:org.app.ds.others.sportclassifier.Classifier.java
     * @description:TODO
     * @author anandm
     * @date Jun 16, 2015 3:30:24 PM
     */
    public class Player {

        private String name;

        private String sport;

        private double height;

        private double weight;

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
        }

    }

    public void initialize(String trainingDataFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(
                trainingDataFile));
        String line = null;
        boolean first = true;
        while ((line = reader.readLine()) != null) {
            if (first) {
                // ignore
                first = false;
            }
            else {
                String[] tokens = line.split(Pattern.quote("\t"));

                players.add(new Player(tokens[0], tokens[1], Double
                        .parseDouble(tokens[2]), Double.parseDouble(tokens[3])));
            }
        }
        reader.close();
    }

    private void normalizeWeights(Player player) {
        double[] values = new double[players.size() + 1];

        for (int i = 0; i < players.size(); i++) {
            values[i] = players.get(i).weight;
        }
        values[values.length - 1] = player.weight;

        double median = DataMiningUtility.median(values);
        double asd = DataMiningUtility
                .absoluteStandardDeviation(values, median);

        values = DataMiningUtility.modifiedStandardScore(values, median, asd);

        for (int i = 0; i < players.size(); i++) {
            players.get(i).weight = values[i];
        }

        player.weight = values[values.length - 1];

    }

    private void normalizeHeight(Player player) {

        double[] values = new double[players.size() + 1];

        for (int i = 0; i < players.size(); i++) {
            values[i] = players.get(i).height;
        }
        values[values.length - 1] = player.height;

        double median = DataMiningUtility.median(values);
        double asd = DataMiningUtility
                .absoluteStandardDeviation(values, median);

        values = DataMiningUtility.modifiedStandardScore(values, median, asd);

        for (int i = 0; i < players.size(); i++) {
            players.get(i).height = values[i];
        }

        player.height = values[values.length - 1];
    }

    private class PlayerDistance implements Comparable<PlayerDistance> {

        private Player player;

        private double distance;

        /**
         * 
         */
        public PlayerDistance() {
            super();

        }

        /**
         * @param player
         * @param distance
         */
        public PlayerDistance(Player player, double distance) {
            super();
            this.player = player;
            this.distance = distance;
        }

        /**
         * @methodName:Classifier.java.compareTo
         * @description:TODO
         * @author anandm
         * @param o
         * @return
         */
        @Override
        public int compareTo(PlayerDistance o) {

            return Double.compare(distance, o.distance);
        }

    }

    private Player nearestNeighbour(Player player) {
        Player nearestNeighBour = null;
        normalizeWeights(player);
        normalizeHeight(player);

        PlayerDistance[] distances = new PlayerDistance[players.size()];

        for (int i = 0; i < players.size(); i++) {
            distances[i] = new PlayerDistance(players.get(i),
                    DataMiningUtility.manhattanDistance(
                            new double[] {}, new double[] {}));
        }

        Arrays.sort(distances);

        return distances[0].player;
    }

    public String classify(double height, double weight) {
        Player nearestNeighbour = nearestNeighbour(new Player("", "", 70, 170));

        return nearestNeighbour.sport;
    }

    public static void main(String[] args) throws IOException {
        Classifier classifier = new Classifier();
        classifier
                .initialize("E:\\Downloads\\Guide To Data Mining Codes\\athletesTrainingSet.txt");
        System.out.println(classifier.classify(70, 170));
    }
}
