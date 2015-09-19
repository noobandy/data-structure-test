package org.app.ds.pricingengine;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @className:org.app.ds.pricingengine.CatalogImpl.java
 * @description:TODO
 * @author anandm
 * @date Sep 1, 2015 2:09:45 PM
 */
public class CatalogImpl implements Catalog {

    private Map<String, Double> catalogMap;

    /**
     * 
     */
    public CatalogImpl() {
        super();
        catalogMap = new HashMap<String, Double>();

    }

    @Override
    public void addItem(String SKU, double unitWisePrice) {
        catalogMap.put(SKU, unitWisePrice);
    }

    @Override
    public void removeItem(String SKU) {
        catalogMap.remove(SKU);
    }

    @Override
    public double getUnitWisePrice(String SKU) {
        Double unitWisePrice = catalogMap.get(SKU);
        if (unitWisePrice == null) {
            throw new IllegalArgumentException("unknown item : " + SKU);
        }
        return unitWisePrice;
    }

    @Override
    public boolean existsItem(String SKU) {

        return catalogMap.containsKey(SKU);
    }

}
