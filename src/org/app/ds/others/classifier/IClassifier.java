/**
 * @filenameName:org.app.ds.others.classifier.IClassifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 17, 2015 10:48:45 AM
 * @version: TODO
 */
package org.app.ds.others.classifier;

import java.util.Collection;

/**
 * @className:org.app.ds.others.classifier.IClassifier.java
 * @description:TODO
 * @author anandm
 * @date Jun 17, 2015 10:48:45 AM
 */
public interface IClassifier {

    void trainClassifier(Collection<? extends IItem> trainingData);

    String classify(double[] featureVector);

}
