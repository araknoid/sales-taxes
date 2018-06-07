package com.araknoid.goods;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ImportedGoodTest {

    private Good bottleOfPerfume = TaxedGood.standard("bottle of perfume", new BigDecimal("47.50"));

    @Test
    public void givenStandardGood_whenImported_thenTaxesAreFivePercentHigher() {
        ImportedGood importedBottleOfPerfume = new ImportedGood(bottleOfPerfume);

        assertEquals(new BigDecimal("7.15"), importedBottleOfPerfume.getTaxes());
    }

    @Test
    public void givenTaxFreeGood_whenImported_thenTaxValueIsFivePercentHigher() {
        Good boxOfChocolates = TaxedGood.exempt("box of chocolates", new BigDecimal("10.00"));

        ImportedGood importedBoxOfChocolates = new ImportedGood(boxOfChocolates);

        assertEquals(new BigDecimal("0.50"), importedBoxOfChocolates.getTaxes());
    }

    @Test
    public void givenTaxedFood_whenImported_thenPriceIsEqual() {
        BigDecimal chocolateBarPrice = new BigDecimal("0.85");
        Good chocolateBar = TaxedGood.standard("chocolate bar", chocolateBarPrice);

        ImportedGood importedChocolateBar = new ImportedGood(chocolateBar);

        assertEquals(chocolateBarPrice, importedChocolateBar.getPrice());
    }

    @Test
    public void givenStandardGood_whenImported_thenPriceWithTaxesIsFivePercentHigher() {
        ImportedGood importedBottleOfPerfume = new ImportedGood(bottleOfPerfume);

        assertEquals(new BigDecimal("54.65"), importedBottleOfPerfume.getPriceWithTaxes());
    }
}
