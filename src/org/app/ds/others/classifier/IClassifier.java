/**
 * @filenameName:org.app.ds.others.classifier.IClassifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 17, 2015 10:48:45 AM
 * @version: TODO
 */
package org.app.ds.others.classifier;


/**
 * @className:org.app.ds.others.classifier.IClassifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 17, 2015 10:48:45 AM
 */
public interface IClassifier {

    void trainClassifier(String itemClass, double[] featureVector);

    String classify(double[] featureVector);

    String classify(double[] featureVector, int k);

}
