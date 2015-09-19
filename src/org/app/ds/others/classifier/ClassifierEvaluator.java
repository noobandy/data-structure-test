/**
 * @filenameName:org.app.ds.others.classifier.ClassifierEvaluator.java
 * @description:TODO
 * @author anandm
 * @date Jun 18, 2015 1:23:24 PM
 * @version: TODO
 */
package org.app.ds.others.classifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * @className:org.app.ds.others.classifier.ClassifierEvaluator.java
 * @description:TODO
 * @author anandm
 * @date Jun 18, 2015 1:23:24 PM
 */
public abstract class ClassifierEvaluator {

    /**
     * @className:org.app.ds.others.classifier.ClassifierEvaluator.java
     * @description:TODO
     * @author anandm
     * @date Jun 18, 2015 1:51:37 PM
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

    private Map<String, List<Item>> itemMap = new HashMap<String, List<Item>>();

    public String evaluate(IClassifier classifier,
                           String testDataFile,
                           String seperator) throws IOException {

        List<List<Item>> buckets = new ArrayList<List<Item>>(10);

        BufferedReader reader = new BufferedReader(new FileReader(testDataFile));

        String line = null;
        int totalItems = 0;
        while ((line = reader.readLine()) != null) {
            totalItems++;
            String[] tokens = line.split(Pattern.quote(seperator));
            String itemClass = itemClass(tokens);
            double[] featureVector = featureVector(tokens);
            List<Item> items = itemMap.get(itemClass);
            if (items == null) {
                items = new ArrayList<ClassifierEvaluator.Item>();
            }
            items.add(new Item(itemClass, featureVector));

            itemMap.put(itemClass, items);
        }
        reader.close();

        int bucketSize = totalItems / 10;

        for (int i = 0; i < 10; i++) {
            int filled = 0;
            for (Entry<String, List<Item>> entry : itemMap.entrySet()) {

            }

        }

        return null;
    }

    public abstract String itemClass(String[] tokens);

    public abstract double[] featureVector(String[] tokens);

}
