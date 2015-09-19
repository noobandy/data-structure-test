package org.app.ds.pricingengine;

/**
 * 
 * @className:org.app.ds.pricingengine.Catalog.java
 * @description:TODO
 * @author anandm
 * @date Sep 1, 2015 2:08:00 PM
 */

public interface Catalog {

    void addItem(String SKU, double unitWisePrice);

    boolean existsItem(String SKU);

    void removeItem(String SKU);

    double getUnitWisePrice(String SKU);
}
