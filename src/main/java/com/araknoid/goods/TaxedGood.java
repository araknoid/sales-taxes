package com.araknoid.goods;

import java.util.Objects;

public class TaxedGood implements Good {

    private final String description;
    private final Amount price;
    private final TaxPolicy taxPolicy;

    private TaxedGood(String description, Amount price, TaxPolicy taxPolicy) {
        this.description = description;
        this.price = price;
        this.taxPolicy = taxPolicy;
    }

    /**
     * Builds an instance of a standard good
     *
     * @param description name of the standard good
     * @param price       price of the standard good
     * @return instance of a standard good
     * @throws NullPointerException if <code>description</code> aor <code>price</code> is null
     */
    public static TaxedGood standard(String description, Amount price) {
        return new TaxedGood(
                Objects.requireNonNull(description),
                Objects.requireNonNull(price),
                TaxPolicy.STANDARD);
    }

    /**
     * Builds an instance of a tax free good
     *
     * @param description name of the tax free good
     * @param price       price of the tax free good
     * @return instance of a tax free good
     * @throws NullPointerException if <code>description</code> aor <code>price</code> is null
     */
    public static TaxedGood exempt(String description, Amount price) {
        return new TaxedGood(
                Objects.requireNonNull(description),
                Objects.requireNonNull(price),
                TaxPolicy.EXEMPT);
    }

    @Override
    public Amount getPrice() {
        return price;
    }

    @Override
    public Amount getTaxes() {
        return taxPolicy.applyTo(price);
    }

    @Override
    public Amount getPriceWithTaxes() {
        return Amount.of(price.asBigDecimal().add(getTaxes().asBigDecimal()));
    }

    @Override
    public String getGoodDescription() {
        return description;
    }
}
