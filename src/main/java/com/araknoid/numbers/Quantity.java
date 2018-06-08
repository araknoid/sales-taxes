package com.araknoid.numbers;

public class Quantity {

    private final int quantityValue;

    public static final Quantity ONE = new Quantity(1);

    public Quantity(int quantityValue) {

        if(quantityValue < 0) {
            throw new IllegalArgumentException("Cannot create a quantity with a negative value");
        }

        this.quantityValue = quantityValue;
    }

    public String print() {
        return String.valueOf(quantityValue);
    }
}