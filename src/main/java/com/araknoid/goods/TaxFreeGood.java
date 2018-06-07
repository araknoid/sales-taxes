package com.araknoid.goods;

import java.math.BigDecimal;
import java.util.Objects;

public class TaxFreeGood implements Good {

    private final String description;
    private final BigDecimal price;

    public static TaxFreeGood of(String description, BigDecimal price) {
        return new TaxFreeGood(
                Objects.requireNonNull(description),
                Objects.requireNonNull(price)
        );
    }

    public TaxFreeGood(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public BigDecimal getTaxes() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getPriceWithTaxes() {
        return price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
