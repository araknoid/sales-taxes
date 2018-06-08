package com.araknoid.goods;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;

public class Amount {

    public static final BigDecimal FIVE_CENTS = new BigDecimal("0.05");

    private final BigDecimal amountValue;

    private Amount(BigDecimal amountValue) {
        this.amountValue = amountValue;
    }

    public static Amount of(BigDecimal amountValue) {
        Objects.requireNonNull(amountValue);

        if (amountValue.compareTo(ZERO) < 0) {
            throw new IllegalArgumentException("Cannot create an amount with a negative value");
        }

        return new Amount(amountValue);
    }

    /**
     * Converts the {@link Amount} to a {@link BigDecimal} representation
     *
     * @return the value of the amount as {@link BigDecimal}
     */
    BigDecimal asBigDecimal() {
        return amountValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Amount amount = (Amount) o;

        return amountValue.equals(amount.amountValue);
    }

    @Override
    public int hashCode() {
        return amountValue.hashCode();
    }

    public Amount roundToNearestFiveCents() {
        return new Amount(amountValue.divide(FIVE_CENTS, 0, RoundingMode.UP).multiply(FIVE_CENTS));
    }
}
