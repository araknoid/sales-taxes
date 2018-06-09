package com.araknoid.numbers;

import com.araknoid.print.Printable;

import java.math.BigDecimal;

/**
 * Represent a positive and natural quantity
 */
public class Quantity implements Printable {

    private final int quantityValue;

    public static final Quantity ONE = of(1);

    private Quantity(int quantityValue) {
        this.quantityValue = quantityValue;
    }

    /**
     * Build an instance of a positive quantity
     *
     * @param quantityValue value that is represented by the {@link Quantity}
     * @return instance of {@link Quantity}
     */
    public static Quantity of(int quantityValue) {

        if (quantityValue < 0) {
            throw new IllegalArgumentException("Cannot create a quantity with a negative value");
        }

        return new Quantity(quantityValue);
    }

    @Override
    public String print() {
        return String.valueOf(quantityValue);
    }

    public BigDecimal asBigDecimal() {
        return new BigDecimal(quantityValue);
    }
}
