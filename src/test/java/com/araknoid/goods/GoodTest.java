package com.araknoid.goods;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GoodTest {

    private Good standardGood = TaxedGood.standard("music CD", Amount.of(new BigDecimal("14.99")));
    private Good taxFreeGood = TaxedGood.exempt("book", Amount.of(new BigDecimal("12.49")));

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

    @Test
    public void givenTaxFreeGood_whenComputingTaxes_thenTaxIsZero() {
        Amount taxes = taxFreeGood.getTaxes();

        assertEquals(Amount.of(new BigDecimal("0.00")), taxes);
    }

    @Test
    public void givenTaxFreeGood_whenComputingPriceWithTaxes_thenPriceWithTaxesIsReturned() {
        Amount priceWithTaxes = taxFreeGood.getPriceWithTaxes();

        assertEquals(taxFreeGood.getPrice(), priceWithTaxes);
    }

    @Test
    public void whenPrintingGood_thenPrintedOutputIsCorrectlyFormatted() {
        assertEquals("music CD: 16.49", standardGood.print());
        assertEquals("book: 12.49", taxFreeGood.print());
    }
}
