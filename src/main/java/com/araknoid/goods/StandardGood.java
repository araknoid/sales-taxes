package com.araknoid.goods;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class StandardGood {

    private final String description;
    private final BigDecimal price;

    private StandardGood(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    /**
     * Builds an instance of a standard good
     *
     * @param description name of the good
     * @param price       price of the good
     * @return instance of a standard good
     * @throws NullPointerException if <code>description</code> aor <code>price</code> is null
     */
    public static StandardGood of(String description, BigDecimal price) {
        return new StandardGood(
                Objects.requireNonNull(description),
                Objects.requireNonNull(price)
        );
    }

    /**
     * Computes the taxes value for the good, rounded to the nearest five cents
     *
     * @return taxes of the good rounded to the nearest five cents
     */
    public BigDecimal getTaxes() {
        BigDecimal taxValue = price.divide(new BigDecimal("100"))
                .multiply(new BigDecimal("10"));
        return roundToNearestFiveCents(taxValue);
    }

    private BigDecimal roundToNearestFiveCents(BigDecimal price) {
        BigDecimal fiveCents = new BigDecimal("0.05");
        return price.divide(fiveCents, 0, RoundingMode.UP)
                .multiply(fiveCents);
    }

    /**
     * Computes the price of the good including the taxes value
     *
     * @return the price of the good, taxes included
     */
    public BigDecimal getPriceWithTaxes() {
        return price.add(getTaxes());
    }
}
