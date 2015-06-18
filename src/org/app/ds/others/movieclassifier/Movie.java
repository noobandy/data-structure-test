/**
 * @filenameName:org.app.ds.others.classifier.Item.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 12:32:49 PM
 * @version: TODO
 */
package org.app.ds.others.movieclassifier;


/**
 * @className:org.app.ds.others.classifier.Item.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 12:32:49 PM
 */
public class Movie {

    private String itemClass;

    private double[] featureVector;

    /**
     * @param itemClass
     * @param featureVector
     */
    public Movie(String itemClass, double[] featureVector) {
        super();
        this.itemClass = itemClass;
        this.featureVector = featureVector;
    }

    /**
     * @methodName:Movie.java.getItemClass
     * @description:TODO
     * @author anandm
     * @return
     */

    public String getItemClass() {

        return itemClass;
    }

    /**
     * @methodName:Movie.java.getFeatureVector
     * @description:TODO
     * @author anandm
     * @return
     */

    public double[] getFeatureVector() {

        return featureVector;
    }

}
