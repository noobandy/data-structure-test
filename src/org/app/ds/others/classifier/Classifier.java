/**
 * @filenameName:org.app.ds.others.classifier.Classifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 17, 2015 10:54:03 AM
 * @version: TODO
 */
package org.app.ds.others.classifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.app.ds.others.DataMiningUtility;

/**
 * @className:org.app.ds.others.classifier.Classifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 17, 2015 10:54:03 AM
 */
public class Classifier implements IClassifier {

    /**
     * @className:org.app.ds.others.classifier.Classifier.java
     * @description:TODO
     * @author anandm
     * @date Jun 18, 2015 12:26:15 PM
     */
    private class ItemClassVotes implements Comparable<ItemClassVotes> {

        private String itemClass;

        private int votes;

        /**
         * @param itemClass
         * @param votes
         */
        public ItemClassVotes(String itemClass, int votes) {
            super();
            this.itemClass = itemClass;
            this.votes = votes;
        }

        /**
         * @methodName:Classifier.java.compareTo
         * @description:TODO
         * @author anandm
         * @param o
         * @return
         */
        @Override
        public int compareTo(ItemClassVotes o) {

            return votes - o.votes;
        }

    }

    /**
     * @className:org.app.ds.others.classifier.Classifier.java
     * @description:TODO
     * @author anandm
     * @date Jun 18, 2015 11:43:32 AM
     */
    private class Item {

        private String itemClass;

        private double[] featureVector;

        /**
         * @param itemClass
         * @param featureVector
         */
        public Item(String itemClass, double[] featureVector) {
            super();
            this.itemClass = itemClass;
            this.featureVector = featureVector;
        }

    }

    /**
     * @className:org.app.ds.others.classifier.Classifier.java
     * @description:TODO
     * @author anandm
     * @date Jun 17, 2015 11:05:41 AM
     */
    private class ItemDistance implements Comparable<ItemDistance> {

        private Item item;

        private double distance;

        /**
         * @param item
         * @param distance
         */
        public ItemDistance(Item item, double distance) {
            super();
            this.item = item;
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
        public int compareTo(ItemDistance o) {

            return Double.compare(distance, o.distance);
        }

    }

    private boolean standardizeFeatureVectors;

    private boolean featureVectorsStandardized;

    private List<Item> items;

    private double[][] medianAndAsd;

    /**
     * 
     */
    public Classifier() {
        this(false);

    }

    /**
     * @param standardizeFeatureVectors
     */
    public Classifier(boolean standardizeFeatureVectors) {
        super();
        this.standardizeFeatureVectors = standardizeFeatureVectors;
        this.items = new ArrayList<Classifier.Item>();
    }

    private void _standardizeFeatureVectors() {
        boolean ready = false;
        int noOfColumns = 0;
        for (Item item : items) {
            if (ready) {

                break;
            }
            else {
                ready = true;
                noOfColumns = item.featureVector.length;
            }
        }
        medianAndAsd = new double[noOfColumns][2];

        for (int i = 0; i < noOfColumns; i++) {
            double[] values = new double[items.size()];
            int j = 0;
            for (Item item : items) {
                values[j++] = item.featureVector[i];
            }

            double median = DataMiningUtility.median(values);
            double asd = DataMiningUtility.absoluteStandardDeviation(
                    values, median);

            medianAndAsd[i][0] = median;
            medianAndAsd[i][1] = asd;

            double[] standardizedValues = DataMiningUtility
                    .modifiedStandardScore(values, median, asd);

            j = 0;
            for (Item item : items) {
                item.featureVector[i] = standardizedValues[j++];
            }
        }
    }

    private List<ItemDistance> _itemDistances(double[] featureVector) {

        List<ItemDistance> itemDistances = new ArrayList<ItemDistance>(
                items.size());

        for (Item item : items) {
            ItemDistance itemDistance = new ItemDistance(item,
                    DataMiningUtility.manhattanDistance(
                            featureVector, item.featureVector));
            itemDistances.add(itemDistance);
        }

        return itemDistances;
    }

    /**
     * @methodName:Classifier.java.classify
     * @description:TODO
     * @author anandm
     * @param featureVector
     * @return
     */
    @Override
    public String classify(double[] featureVector) {

        if (!featureVectorsStandardized && standardizeFeatureVectors) {
            _standardizeFeatureVectors();
        }

        if (standardizeFeatureVectors) {
            featureVectorsStandardized = true;
            // standardize feature vector
            for (int i = 0; i < featureVector.length; i++) {
                featureVector[i] = (featureVector[i] - medianAndAsd[i][0])
                        / medianAndAsd[i][1];
            }
        }
        List<ItemDistance> itemDistances = _itemDistances(featureVector);

        Collections.sort(itemDistances);

        Item nearestNeighbour = itemDistances.get(0).item;

        return nearestNeighbour.itemClass;
    }

    /**
     * @methodName:Classifier.java.trainClassifier
     * @description:TODO
     * @author anandm
     * @param itemClass
     * @param featureVector
     */
    @Override
    public void trainClassifier(String itemClass, double[] featureVector) {

        if (featureVectorsStandardized) {
            throw new IllegalStateException("classifier is already trained");
        }

        Item item = new Item(itemClass, featureVector);
        items.add(item);
    }

    /**
     * @methodName:Classifier.java.classify
     * @description:TODO
     * @author anandm
     * @param featureVector
     * @param k
     * @return
     */
    @Override
    public String classify(double[] featureVector, int k) {

        if (!featureVectorsStandardized && standardizeFeatureVectors) {
            _standardizeFeatureVectors();
        }

        if (standardizeFeatureVectors) {
            featureVectorsStandardized = true;
            // standardize feature vector
            for (int i = 0; i < featureVector.length; i++) {
                featureVector[i] = (featureVector[i] - medianAndAsd[i][0])
                        / medianAndAsd[i][1];
            }
        }
        List<ItemDistance> itemDistances = _itemDistances(featureVector);
        Collections.sort(itemDistances);

        Map<String, ItemClassVotes> classVotes = new HashMap<String, ItemClassVotes>();

        for (int i = 0; i < k; i++) {
            Item item = itemDistances.get(i).item;

            ItemClassVotes itemClassVotes = classVotes.get(item.itemClass);

            if (itemClassVotes == null) {
                itemClassVotes = new ItemClassVotes(item.itemClass, 0);
            }

            itemClassVotes.votes++;

            classVotes.put(item.itemClass, itemClassVotes);
        }

        List<ItemClassVotes> itemClassVotesList = new ArrayList<Classifier.ItemClassVotes>(
                classVotes.values());

        Collections.sort(itemClassVotesList);

        return itemClassVotesList.get(0).itemClass;
    }
}
