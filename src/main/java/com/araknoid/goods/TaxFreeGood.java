package com.araknoid.goods;

import java.math.BigDecimal;
import java.util.Objects;

public class TaxFreeGood implements Good {

    private final String description;
    private final BigDecimal price;
    private final TaxPolicy taxPolicy;

    public static TaxFreeGood of(String description, BigDecimal price) {
        return new TaxFreeGood(
                Objects.requireNonNull(description),
                Objects.requireNonNull(price),
                TaxPolicy.EXEMPT);
    }

    public TaxFreeGood(String description, BigDecimal price, TaxPolicy taxPolicy) {
        this.description = description;
        this.price = price;
        this.taxPolicy = taxPolicy;
    }

    @Override
    public BigDecimal getTaxes() {
        return taxPolicy.applyTo(price);
    }

    @Override
    public BigDecimal getPriceWithTaxes() {
        return price.add(getTaxes());
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
