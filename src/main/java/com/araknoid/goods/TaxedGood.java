package com.araknoid.goods;

import java.math.BigDecimal;
import java.util.Objects;

public class TaxedGood implements Good {

    private final String description;
    private final BigDecimal price;
    private final TaxPolicy taxPolicy;

    private TaxedGood(String description, BigDecimal price, TaxPolicy taxPolicy) {
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
    public static TaxedGood standard(String description, BigDecimal price) {
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
    public static TaxedGood exempt(String description, BigDecimal price) {
        return new TaxedGood(
                Objects.requireNonNull(description),
                Objects.requireNonNull(price),
                TaxPolicy.EXEMPT);
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
