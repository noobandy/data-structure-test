package org.app.ds.pricingengine;

public class MultiPricedOffer extends Offer {

    private int noOfUnit;

    private double price;

    public MultiPricedOffer(String SKU, int priority, int noOfUnit, double price) {
        super();
        super.applicableForSKU = SKU;
        super.priority = priority;
        this.noOfUnit = noOfUnit;
        this.price = price;
    }

    public String getApplicableForSKU() {
        return super.applicableForSKU;
    }

    public int getPriority() {
        return super.priority;
    }

    public int getNoOfUnit() {
        return noOfUnit;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int applicableOnUnits(int totalUnits) {
        int remaining = totalUnits % noOfUnit;
        return totalUnits - remaining;
    }

    @Override
    public double price(int totalUnits) {
        int times = totalUnits / noOfUnit;

        return times * price;
    }

    @Override
    public boolean isApplicable(String SKU, int totalUnits) {

        return SKU.equals(super.applicableForSKU)
                && (applicableOnUnits(totalUnits) > 0);
    }

}
