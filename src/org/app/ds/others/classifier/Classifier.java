/**
 * @filenameName:org.app.ds.others.classifier.Classifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 12:37:33 PM
 * @version: TODO
 */
package org.app.ds.others.classifier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.app.ds.others.DataMiningUtility;

/**
 * @className:org.app.ds.others.classifier.Classifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 12:37:33 PM
 */
public class Classifier {

    /**
     * 
     */
    public Classifier() {
        super();

    }

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

    private Item nearestNeighbour(Item item, Item[] items) {
        List<ItemDistance> itemDistances = new ArrayList<Classifier.ItemDistance>(
                items.length);

        for (int i = 0; i < items.length; i++) {
            itemDistances.add(new ItemDistance(items[i], DataMiningUtility
                    .manhattanDistance(
                            item.getFeatureVector(),
                            items[i].getFeatureVector())));
        }

        Collections.sort(itemDistances);

        return itemDistances.get(0).item;

    }

    public boolean classify(User user, Item item, Item[] items) {
        boolean mayLike = false;

        Item nearestNeighbour = nearestNeighbour(item, items);

        String[] likedItems = Arrays.copyOf(
                user.getLikedItemIds(), user.getLikedItemIds().length);

        Arrays.sort(likedItems);

        if (Arrays.binarySearch(likedItems, nearestNeighbour.getId()) >= 0) {
            mayLike = true;

        }
        return mayLike;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Classifier classifier = new Classifier();

        System.out.println(classifier.classify(
                new User("Angelica", new String[] { "Dr Dog/Fate",
                        "Phoenix/Lisztomania", "Glee Cast/Jessie's Girl",
                        "Lady Gaga/Alejandro" }, new String[] {
                        "Heartless Bastards/Out at Sea",
                        "Todd Snider/Don't Tempt Me",
                        "The Black Keys/Magic Potion", "La Roux/Bulletproof",
                        "Black Eyed Peas/Rock That Body", "Mike Posner" }),
                new Item("Chris Cagle/ I Breathe In. I Breathe Out",
                        new double[] { 1, 5, 2.5, 1, 1, 5, 1 }), new Item[] {
                        new Item("Dr Dog/Fate", new double[] { 2.5, 4, 3.5, 3,
                                5, 4, 1 }),
                        new Item("Phoenix/Lisztomania", new double[] { 2, 5, 5,
                                3, 2, 1, 1 }),
                        new Item("Heartless Bastards/Out at Sea", new double[] {
                                1, 5, 4, 2, 4, 1, 1 }),
                        new Item("Todd Snider/Don't Tempt Me", new double[] {
                                4, 5, 4, 4, 1, 5, 1 }),
                        new Item("The Black Keys/Magic Potion", new double[] {
                                1, 4, 5, 3.5, 5, 1, 1 }),
                        new Item("Glee Cast/Jessie's Girl", new double[] { 1,
                                5, 3.5, 3, 4, 5, 1 }),
                        new Item("La Roux/Bulletproof", new double[] { 5, 5, 4,
                                2, 1, 1, 1 }),
                        new Item("Mike Posner", new double[] { 2.5, 4, 4, 1, 1,
                                1, 1 }),
                        new Item("Black Eyed Peas/Rock That Body",
                                new double[] { 2, 5, 5, 1, 2, 2, 4 }),
                        new Item("Lady Gaga/Alejandro", new double[] { 1, 5, 3,
                                2, 1, 2, 1 }) }));

        //

        List<Item> sportItems = new ArrayList<Item>();

        Item itemToClassify = new Item();

        BufferedReader reader = new BufferedReader(new FileReader(""));

    }
}
