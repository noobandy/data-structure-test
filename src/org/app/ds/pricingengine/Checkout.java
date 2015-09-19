package org.app.ds.pricingengine;

/**
 * @className:org.app.ds.pricingengine.Checkout.java
 * @description:TODO
 * @author anandm
 * @date Sep 1, 2015 2:04:30 PM
 */
public interface Checkout {

    void scan(String SKU);

    void scan(String... SKU);

    double total();
}
