/**
 * @filenameName:org.app.ds.pricingengine.CheckoutImpl.java
 * @description:TODO
 * @author anandm
 * @date Sep 1, 2015 2:05:35 PM
 * @version: TODO
 */
package org.app.ds.pricingengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @className:org.app.ds.pricingengine.CheckoutImpl.java
 * @description:TODO
 * @author anandm
 * @date Sep 1, 2015 2:05:35 PM
 */
public class CheckoutImpl implements Checkout {

    private Catalog catalog;

    private List<Offer> offers;

    private Map<String, Integer> SKUUnits;

    /**
     * 
     */
    public CheckoutImpl(Catalog catalog, List<Offer> offers) {
        super();
        this.catalog = catalog;
        this.offers = offers;
        SKUUnits = new HashMap<String, Integer>();

    }

    @Override
    public void scan(String SKU) {
        if (!catalog.existsItem(SKU)) {
            throw new IllegalArgumentException("unknown SKU " + SKU);
        }

        Integer noOfUnits = SKUUnits.get(SKU);

        if (noOfUnits == null) {
            noOfUnits = 0;
        }
        noOfUnits++;

        SKUUnits.put(SKU, noOfUnits);
    }

    @Override
    public void scan(String... SKU) {
        for (String sku : SKU) {
            scan(sku);
        }
    }

    @Override
    public double total() {
        double total = 0.0;
        for (Entry<String, Integer> entry : SKUUnits.entrySet()) {
            Offer offer = applicableOffer(entry.getKey(), entry.getValue());

            int remaningUnits = entry.getValue();

            if (offer != null) {
                remaningUnits = entry.getValue()
                        - offer.applicableOnUnits(entry.getValue());
                total = total + offer.price(entry.getValue());
            }

            total = total
                    + (remaningUnits * catalog.getUnitWisePrice(entry.getKey()));
        }
        return total;
    }

    private Offer applicableOffer(String SKU, int totalUnits) {
        List<Offer> applicableOffers = new ArrayList<Offer>();

        for (Offer offer : offers) {
            if (offer.isApplicable(SKU, totalUnits)) {
                applicableOffers.add(offer);
            }
        }

        Collections.sort(applicableOffers, new Comparator<Offer>() {

            @Override
            public int compare(Offer o1, Offer o2) {

                return o1.priority - o2.priority;
            }

        });

        if (!applicableOffers.isEmpty()) {
            return applicableOffers.get(applicableOffers.size() - 1);
        }

        return null;
    }

}
