package org.app.ds.pricingengine;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @className:org.app.ds.pricingengine.App.java
 * @description:TODO
 * @author anandm
 * @date Sep 1, 2015 2:51:39 PM
 */
public class App {

    public static void main(String[] args) {
        Catalog catalog = new CatalogImpl();
        catalog.addItem("A", 50);
        catalog.addItem("B", 30);
        catalog.addItem("C", 20);
        catalog.addItem("D", 15);

        List<Offer> offers = new ArrayList<Offer>();

        offers.add(new MultiPricedOffer("A", 1, 3, 130));

        offers.add(new MultiPricedOffer("B", 1, 2, 45));

        // 1
        Checkout checkout = new CheckoutImpl(catalog, offers);

        checkout.scan("D", "A", "B", "A", "B", "A");

        System.out.println(Double.compare(190, checkout.total()) == 0);

        // 2
        checkout = new CheckoutImpl(catalog, offers);

        checkout.scan("A", "A", "A", "A", "A", "A");

        System.out.println(Double.compare(260, checkout.total()) == 0);

        // 3
        checkout = new CheckoutImpl(catalog, offers);

        checkout.scan("C", "D", "B", "A");

        System.out.println(Double.compare(115, checkout.total()) == 0);

        // 4
        checkout = new CheckoutImpl(catalog, offers);

        checkout.scan("A", "A");

        System.out.println(Double.compare(100, checkout.total()) == 0);

        // 5
        checkout = new CheckoutImpl(catalog, offers);

        checkout.scan("A", "A", "A");

        System.out.println(Double.compare(130, checkout.total()) == 0);

    }

}
