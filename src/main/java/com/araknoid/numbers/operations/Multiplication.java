package com.araknoid.numbers.operations;

import com.araknoid.numbers.Amount;
import com.araknoid.numbers.Quantity;

import java.util.Objects;

/**
 * Represents the multiplication between an {@link Amount} and a {@link Quantity}
 */
public class Multiplication {

    private final Amount amount;
    private final Quantity quantity;

    private Multiplication(Amount amount, Quantity quantity) {
        this.amount = amount;
        this.quantity = quantity;
    }

    public static Multiplication of(Amount amount, Quantity quantity) {
        return new Multiplication(
                Objects.requireNonNull(amount),
                Objects.requireNonNull(quantity)
        );
    }

    public Amount asAmount() {
        return Amount.of(amount.asBigDecimal().multiply(quantity.asBigDecimal()));
    }
}
