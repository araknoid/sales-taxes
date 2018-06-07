package com.araknoid.goods;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class StandardGoodTest {

    private StandardGood standardGood = StandardGood.of("music CD", new BigDecimal("14.99"));

    @Test
    public void givenStandardGood_whenComputingTaxes_thenTaxValueIsReturned() {
        BigDecimal taxes = standardGood.getTaxes();

        assertEquals(new BigDecimal("1.50"), taxes);
    }

    @Test
    public void givenStandardGood_whenComputingPriceWithTaxes_thenPriceWithTaxesIsReturned() {
        BigDecimal priceWithTaxes = standardGood.getPriceWithTaxes();

        assertEquals(new BigDecimal("16.49"), priceWithTaxes);
    }
}
