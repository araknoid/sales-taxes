package com.araknoid.numbers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuantityTest {

    @Test(expected = IllegalArgumentException.class)
    public void givenNegativeQuantityValue_whenBuildingQuantity_thenException() {
        Quantity.of(-1);
    }

    @Test
    public void whenPrintingQuantity_thenPrintedQuantityValueIsCorrect() {
        assertEquals("11", Quantity.of(11).print());
    }
}
