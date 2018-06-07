package com.araknoid.goods;

import java.math.BigDecimal;

public interface Good {

    /**
     * Extract the price from the good
     *
     * @return the price of the good
     */
    public BigDecimal getPrice();

    /**
     * Computes the taxes value for the good, rounded to the nearest five cents
     *
     * @return taxes of the good rounded to the nearest five cents
     */
    public BigDecimal getTaxes();

    /**
     * Computes the price of the good including the taxes value
     *
     * @return the price of the good, taxes included
     */
    public BigDecimal getPriceWithTaxes();
}
