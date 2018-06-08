package com.araknoid.bucket;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuantityTest {

    @Test(expected = IllegalArgumentException.class)
    public void givenNegativeQuantityValue_whenBuildingQuantity_thenException() {
        new Quantity(-1);
    }

    @Test
    public void whenPrintingQuantity_thenPrintedQuantityValueIsCorrect() {
        assertEquals("11", new Quantity(11).print());
    }
}
