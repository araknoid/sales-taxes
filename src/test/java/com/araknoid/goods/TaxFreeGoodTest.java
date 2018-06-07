package com.araknoid.goods;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxFreeGoodTest {

    private Good taxFreeGood = TaxedGood.exempt("book", new BigDecimal("12.49"));

    @Test
    public void givenTaxFreeGood_whenComputingTaxes_thenTaxIsZero() {
        BigDecimal taxes = taxFreeGood.getTaxes();

        assertEquals(new BigDecimal("0.00"), taxes);
    }

    @Test
    public void givenStandardGood_whenComputingPriceWithTaxes_thenPriceWithTaxesIsReturned() {
        BigDecimal priceWithTaxes = taxFreeGood.getPriceWithTaxes();

        assertEquals(taxFreeGood.getPrice(), priceWithTaxes);
    }
}
