package com.araknoid.goods;

import java.math.BigDecimal;
import java.util.Objects;

public class StandardGood implements Good {

    private final String description;
    private final BigDecimal price;
    private final TaxPolicy taxPolicy;

    private StandardGood(String description, BigDecimal price, TaxPolicy taxPolicy) {
        this.description = description;
        this.price = price;
        this.taxPolicy = taxPolicy;
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
                Objects.requireNonNull(price),
                TaxPolicy.STANDARD);
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public BigDecimal getTaxes() {
        return taxPolicy.applyTo(price);
    }

    @Override
    public BigDecimal getPriceWithTaxes() {
        return price.add(getTaxes());
    }
}
