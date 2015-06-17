/**
 * @filenameName:org.app.ds.others.classifier.Classifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 17, 2015 10:54:03 AM
 * @version: TODO
 */
package org.app.ds.others.classifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
     * @date Jun 17, 2015 11:05:41 AM
     */
    private class ItemDistance implements Comparable<ItemDistance> {

        private IItem item;

        private double distance;

        /**
         * @param item
         * @param distance
         */
        public ItemDistance(IItem item, double distance) {
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

    private Collection<? extends IItem> items;

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
        this.items = Collections.emptyList();
    }

    private void _standardizeFeatureVectors() {
        boolean ready = false;
        int noOfColumns = 0;
        for (IItem item : items) {
            if (ready) {

                break;
            }
            else {
                ready = true;
                noOfColumns = item.getFeatureVector().length;
            }
        }
        medianAndAsd = new double[noOfColumns][2];

        for (int i = 0; i < noOfColumns; i++) {
            double[] values = new double[items.size()];
            int j = 0;
            for (IItem item : items) {
                values[j++] = item.getFeatureVector()[i];
            }

            double median = DataMiningUtility.median(values);
            double asd = DataMiningUtility.absoluteStandardDeviation(
                    values, median);

            medianAndAsd[i][0] = median;
            medianAndAsd[i][1] = asd;

            double[] standardizedValues = DataMiningUtility
                    .modifiedStandardScore(values, median, asd);

            j = 0;
            for (IItem item : items) {
                item.getFeatureVector()[i] = standardizedValues[j++];
            }
        }
    }

    private IItem _nearestNeighbour(double[] featureVector) {

        List<ItemDistance> itemDistances = new ArrayList<ItemDistance>(
                items.size());

        for (IItem item : items) {
            ItemDistance itemDistance = new ItemDistance(item,
                    DataMiningUtility.manhattanDistance(
                            featureVector, item.getFeatureVector()));
            itemDistances.add(itemDistance);
        }

        Collections.sort(itemDistances);

        return itemDistances.get(0).item;
    }

    /**
     * @methodName:Classifier.java.trainClassifier
     * @description:TODO
     * @author anandm
     * @param trainingData
     */
    @Override
    public void trainClassifier(Collection<? extends IItem> trainingData) {
        this.items = trainingData;
        if (standardizeFeatureVectors) {
            _standardizeFeatureVectors();
        }
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

        if (standardizeFeatureVectors) {
            // standardize feature vector
            for (int i = 0; i < featureVector.length; i++) {
                featureVector[i] = (featureVector[i] - medianAndAsd[i][0])
                        / medianAndAsd[i][1];
            }
        }

        IItem nearestNeighbour = _nearestNeighbour(featureVector);

        return nearestNeighbour.getItemClass();
    }

}
