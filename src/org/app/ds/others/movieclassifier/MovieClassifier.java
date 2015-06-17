/**
 * @filenameName:org.app.ds.others.classifier.Classifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 12:37:33 PM
 * @version: TODO
 */
package org.app.ds.others.movieclassifier;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;

import org.app.ds.others.classifier.Classifier;
import org.app.ds.others.classifier.IClassifier;

/**
 * @className:org.app.ds.others.classifier.Classifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 12:37:33 PM
 */
public class MovieClassifier {

    private IClassifier classifier;

    /**
     * 
     */
    public MovieClassifier() {
        super();
        classifier = new Classifier();
    }

    public boolean prdictIfUserMayLike(User user, double[] featureVecotor, Movie[] items) {
        boolean mayLike = false;

        Collection<Movie> movies = Arrays.asList(items);

        classifier.trainClassifier(movies);

        String itemClass = classifier.classify(featureVecotor);

        String[] likedItems = Arrays.copyOf(
                user.getLikedItemIds(), user.getLikedItemIds().length);

        Arrays.sort(likedItems);

        if (Arrays.binarySearch(likedItems, itemClass) >= 0) {
            mayLike = true;

        }
        return mayLike;
    }

    public static void main(String[] args) throws FileNotFoundException {
        MovieClassifier classifier = new MovieClassifier();

        System.out.println(classifier.prdictIfUserMayLike(
                new User("Angelica", new String[] { "Dr Dog/Fate",
                        "Phoenix/Lisztomania", "Glee Cast/Jessie's Girl",
                        "Lady Gaga/Alejandro" }, new String[] {
                        "Heartless Bastards/Out at Sea",
                        "Todd Snider/Don't Tempt Me",
                        "The Black Keys/Magic Potion", "La Roux/Bulletproof",
                        "Black Eyed Peas/Rock That Body", "Mike Posner" }),

                new double[] { 1, 5, 2.5, 1, 1, 5, 1 }, new Movie[] {
                        new Movie("Dr Dog/Fate", new double[] { 2.5, 4, 3.5, 3,
                                5, 4, 1 }),
                        new Movie("Phoenix/Lisztomania", new double[] { 2, 5,
                                5, 3, 2, 1, 1 }),
                        new Movie("Heartless Bastards/Out at Sea",
                                new double[] { 1, 5, 4, 2, 4, 1, 1 }),
                        new Movie("Todd Snider/Don't Tempt Me", new double[] {
                                4, 5, 4, 4, 1, 5, 1 }),
                        new Movie("The Black Keys/Magic Potion", new double[] {
                                1, 4, 5, 3.5, 5, 1, 1 }),
                        new Movie("Glee Cast/Jessie's Girl", new double[] { 1,
                                5, 3.5, 3, 4, 5, 1 }),
                        new Movie("La Roux/Bulletproof", new double[] { 5, 5,
                                4, 2, 1, 1, 1 }),
                        new Movie("Mike Posner", new double[] { 2.5, 4, 4, 1,
                                1, 1, 1 }),
                        new Movie("Black Eyed Peas/Rock That Body",
                                new double[] { 2, 5, 5, 1, 2, 2, 4 }),
                        new Movie("Lady Gaga/Alejandro", new double[] { 1, 5,
                                3, 2, 1, 2, 1 }) }));

    }
}
