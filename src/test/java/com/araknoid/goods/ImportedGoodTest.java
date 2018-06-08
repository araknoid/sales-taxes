package com.araknoid.goods;

import com.araknoid.numbers.Amount;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ImportedGoodTest {

    private Good bottleOfPerfume = TaxedGood.standard("bottle of perfume", Amount.of(new BigDecimal("47.50")));
    private Good chocolateBar = TaxedGood.exempt("chocolate bar", Amount.of(new BigDecimal("0.85")));

    @Test
    public void givenStandardGood_whenImported_thenTaxesAreFivePercentHigher() {
        ImportedGood importedBottleOfPerfume = new ImportedGood(bottleOfPerfume);

        assertEquals(Amount.of(new BigDecimal("7.15")), importedBottleOfPerfume.getTaxes());
    }

    @Test
    public void givenTaxFreeGood_whenImported_thenTaxValueIsFivePercentHigher() {
        Good boxOfChocolates = TaxedGood.exempt("box of chocolates", Amount.of(new BigDecimal("10.00")));

        ImportedGood importedBoxOfChocolates = new ImportedGood(boxOfChocolates);

        assertEquals(Amount.of(new BigDecimal("0.50")), importedBoxOfChocolates.getTaxes());
    }

    @Test
    public void givenTaxedFood_whenImported_thenPriceIsEqual() {
        ImportedGood importedChocolateBar = new ImportedGood(chocolateBar);

        assertEquals(chocolateBar.getPrice(), importedChocolateBar.getPrice());
    }

    @Test
    public void givenStandardGood_whenImported_thenPriceWithTaxesIsFivePercentHigher() {
        ImportedGood importedBottleOfPerfume = new ImportedGood(bottleOfPerfume);

        assertEquals(Amount.of(new BigDecimal("54.65")), importedBottleOfPerfume.getPriceWithTaxes());
    }

    @Test
    public void whenPrintingImportedGood_thenImportedGoodIsPrintedCorrectly() {
        ImportedGood importedChocolateBar = new ImportedGood(chocolateBar);

        assertEquals("imported chocolate bar: 0.90", importedChocolateBar.print());
    }
}
