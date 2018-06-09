package com.araknoid.numbers;

import java.math.BigDecimal;

public class Quantity {

    private final int quantityValue;

    public static final Quantity ONE = of(1);

    private Quantity(int quantityValue) {
        this.quantityValue = quantityValue;
    }

    /**
     *
     * @param quantityValue
     * @return
     */
    public static Quantity of(int quantityValue) {

        if(quantityValue < 0) {
            throw new IllegalArgumentException("Cannot create a quantity with a negative value");
        }

        return new Quantity(quantityValue);
    }

    public String print() {
        return String.valueOf(quantityValue);
    }

    public BigDecimal asBigDecimal() {
        return new BigDecimal(quantityValue);
    }
}
