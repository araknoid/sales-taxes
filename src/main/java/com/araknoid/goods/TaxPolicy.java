package com.araknoid.goods;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum TaxPolicy {

    STANDARD(new BigDecimal("10")), EXEMPT(BigDecimal.ZERO);

    public static final BigDecimal HUNDRED = new BigDecimal("100");
    public static final BigDecimal FIVE_CENTS = new BigDecimal("0.05");

    private final BigDecimal taxValue;

    TaxPolicy(BigDecimal taxValue) {
        this.taxValue = taxValue;
    }

    /**
     * Computes the tax value for the provided <code>price</code>
     *
     * @param price Price from which we have to compute the tax value
     * @return tax value computed from the <code>price</code>
     */
    public BigDecimal applyTo(BigDecimal price) {
        BigDecimal computedTaxes = price.divide(HUNDRED).multiply(taxValue);
        return roundToNearestFiveCents(computedTaxes);
    }

    private BigDecimal roundToNearestFiveCents(BigDecimal price) {
        return price.divide(FIVE_CENTS, 0, RoundingMode.UP).multiply(FIVE_CENTS);
    }
}
