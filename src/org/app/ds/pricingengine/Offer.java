package org.app.ds.pricingengine;

public abstract class Offer {

    protected String applicableForSKU;

    protected int priority;

    public abstract int applicableOnUnits(int totalUnits);

    public abstract double price(int totalUnits);

    public abstract boolean isApplicable(String SKU, int totalUnits);
}
