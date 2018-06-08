package com.araknoid.goods;

import com.araknoid.numbers.Amount;

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
        return good.getTaxes().add(additionalTaxes);
    }

    @Override
    public Amount getPriceWithTaxes() {
        return good.getPrice().add(getTaxes());
    }

    @Override
    public String getGoodDescription() {
        return "imported " + good.getGoodDescription();
    }
}
