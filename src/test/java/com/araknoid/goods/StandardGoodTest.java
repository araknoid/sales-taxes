package com.araknoid.goods;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class StandardGoodTest {

    private Good standardGood = TaxedGood.standard("music CD", Amount.of(new BigDecimal("14.99")));

    @Test
    public void givenStandardGood_whenComputingTaxes_thenTaxValueIsReturned() {
        Amount taxes = standardGood.getTaxes();

        assertEquals(Amount.of(new BigDecimal("1.50")), taxes);
    }

    @Test
    public void givenStandardGood_whenComputingPriceWithTaxes_thenPriceWithTaxesIsReturned() {
        Amount priceWithTaxes = standardGood.getPriceWithTaxes();

        assertEquals(Amount.of(new BigDecimal("16.49")), priceWithTaxes);
    }
}
