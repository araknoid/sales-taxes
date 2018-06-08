package com.araknoid.goods;

import java.math.BigDecimal;

/**
 * Tax policy that can be applied to a {@link Good}
 */
enum TaxPolicy {

    STANDARD(new BigDecimal("10")), EXEMPT(BigDecimal.ZERO), IMPORTED(new BigDecimal("5"));

    public static final BigDecimal HUNDRED = new BigDecimal("100");

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
    public Amount applyTo(Amount price) {
        BigDecimal computedTaxAmount = price.asBigDecimal().divide(HUNDRED).multiply(taxValue);
        return Amount.of(computedTaxAmount).roundToNearestFiveCents();
    }
}
