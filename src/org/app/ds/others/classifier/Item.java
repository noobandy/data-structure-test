/**
 * @filenameName:org.app.ds.others.classifier.Item.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 12:32:49 PM
 * @version: TODO
 */
package org.app.ds.others.classifier;


/**
 * @className:org.app.ds.others.classifier.Item.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 12:32:49 PM
 */
public class Item {

    private String id;

    private double[] featureVector;

    /**
     * 
     */
    public Item() {
        super();

    }

    /**
     * @param id
     * @param featureVector
     */
    public Item(String id, double[] featureVector) {
        super();
        this.id = id;
        this.featureVector = featureVector;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double[] getFeatureVector() {
        return featureVector;
    }

    public void setFeatureVector(double[] featureVector) {
        this.featureVector = featureVector;
    }

}
