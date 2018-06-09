package com.araknoid.numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public class Amount {

    public static final BigDecimal FIVE_CENTS = new BigDecimal("0.05");
    public static final Amount ZERO = new Amount(new BigDecimal("0.00"));

    private final BigDecimal amountValue;

    private Amount(BigDecimal amountValue) {
        this.amountValue = amountValue;
    }

    /**
     * Build an instance of a positive only {@link Amount} from the given <code>amountValue</code>
     *
     * @param amountValue value that the quantity will represent
     * @return {@link Amount} representing the <code>amountValue</code>
     * @throws NullPointerException     if the <code>amountValue</code> is null
     * @throws IllegalArgumentException if the <code>amountValue</code> is negative
     */
    public static Amount of(BigDecimal amountValue) {
        Objects.requireNonNull(amountValue);

        if (amountValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot create an amount with a negative value");
        }

        return new Amount(amountValue);
    }

    /**
     * Converts the {@link Amount} to a {@link BigDecimal} representation
     *
     * @return the value of the amount as {@link BigDecimal}
     */
    public BigDecimal asBigDecimal() {
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

    /**
     * Rounds the {@link Amount} to the nearest 5 cents
     * These are the rounding applied:
     * <ul>
     * <li>amount with 0 cents, no rounding is applied</li>
     * <li>amount with 1-5 cents, rounded to 5 cents</li>
     * <li>amount with 6-9 cents, rounded to the next 10 cents</li>
     * </ul>
     * Examples:
     * <ul>
     * <li>11.03 will be rounded to 11.05</li>
     * <li>9.99 will be rounded to 10.00</li>
     * </ul>
     *
     * @return rounded {@link Amount} to the nearest 5 cents
     */
    public Amount roundToNearestFiveCents() {
        return new Amount(amountValue.divide(FIVE_CENTS, 0, RoundingMode.UP).multiply(FIVE_CENTS));
    }

    public String print() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(amountValue.stripTrailingZeros());
    }

    /**
     * Add the addend {@link Amount}
     * If the <code>addend</code> is <code>null</code>, it will return the same amount
     *
     * @param addend {@link Amount} to be added
     * @return a new instance of {@link Amount} representing the addition of the <code>addend</code> to the amount
     */
    public Amount add(Amount addend) {

        if (addend == null) {
            return this;
        }

        return new Amount(amountValue.add(addend.asBigDecimal()));
    }
}
