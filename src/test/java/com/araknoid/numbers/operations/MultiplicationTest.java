package com.araknoid.numbers.operations;

import com.araknoid.numbers.Amount;
import com.araknoid.numbers.Quantity;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MultiplicationTest {

    @Test
    public void givenAmountAndQuantity_whenMultiplied_thenReturnedAmountRepresentingTheMultiplication() {
        Quantity quantity = new Quantity(5);
        Amount amount = Amount.of(new BigDecimal("10.05"));

        Multiplication multiplication = Multiplication.of(amount, quantity);

        assertEquals(Amount.of(new BigDecimal("50.25")), multiplication.asAmount());
    }
}
