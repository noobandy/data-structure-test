/**
 * @filenameName:org.app.ds.others.classifier.GeneralClassifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 4:14:33 PM
 * @version: TODO
 */
package org.app.ds.others.classifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @className:org.app.ds.others.classifier.GeneralClassifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 4:14:33 PM
 */
public class AbstractClassifier {

    private List<Item> items = new ArrayList<AbstractClassifier.Item>();

    private double[][] medianAndAsd;

    private class Item {

        private String itemClass;

        private double[] featureValues;

        /**
         * 
         */
        public Item() {
            super();

        }

        /**
         * @param itemClass
         * @param featureValues
         */
        public Item(String itemClass, double[] featureValues) {
            super();
            this.itemClass = itemClass;
            this.featureValues = featureValues;
        }
    }

    private void normalizeColumns() {

    }

    private String classify(double[] featureValues) {
        String itemClass = null;
        return itemClass;
    }
}
