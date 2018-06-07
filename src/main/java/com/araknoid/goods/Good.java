package com.araknoid.goods;

import java.math.BigDecimal;

public interface Good {

    /**
     * Extract the price from the good
     *
     * @return the price standard the good
     */
    public BigDecimal getPrice();

    /**
     * Computes the taxes value for the good
     *
     * @return taxes standard the good
     */
    public BigDecimal getTaxes();

    /**
     * Computes the price standard the good including the taxes value
     *
     * @return the price standard the good, taxes included
     */
    public BigDecimal getPriceWithTaxes();
}
