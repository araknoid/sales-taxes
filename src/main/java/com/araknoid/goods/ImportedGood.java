package com.araknoid.goods;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Decorates a {@link TaxedGood}, either standard or exempt, with the taxes values for being an imported good
 */
public class ImportedGood implements Good {

    private final Good good;

    public ImportedGood(Good good) {
        this.good = Objects.requireNonNull(good);
    }

    @Override
    public BigDecimal getPrice() {
        return good.getPrice();
    }

    @Override
    public BigDecimal getTaxes() {
        return good.getTaxes().add(TaxPolicy.IMPORTED.applyTo(good.getPrice()));
    }

    @Override
    public BigDecimal getPriceWithTaxes() {
        return good.getPrice().add(getTaxes());
    }
}
