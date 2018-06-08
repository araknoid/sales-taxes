package com.araknoid.goods;

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
    public Amount getPrice() {
        return good.getPrice();
    }

    @Override
    public Amount getTaxes() {
        Amount additionalTaxes = TaxPolicy.IMPORTED.applyTo(good.getPrice());
        return Amount.of(good.getTaxes().asBigDecimal().add(additionalTaxes.asBigDecimal()));
    }

    @Override
    public Amount getPriceWithTaxes() {
        return Amount.of(good.getPrice().asBigDecimal().add(getTaxes().asBigDecimal()));
    }

    @Override
    public String getGoodDescription() {
        return "imported " + good.getGoodDescription();
    }
}
