package com.araknoid.goods;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxPolicyTest {

    private Amount price = Amount.of(new BigDecimal("16.99"));

    @Test
    public void givenPrice_whenComputingStandardTax_thenTaxValueIsCorrect() {
        Amount taxes = TaxPolicy.STANDARD.applyTo(price);

        assertEquals(Amount.of(new BigDecimal("1.70")), taxes);
    }

    @Test
    public void givenPrice_whenComputingTaxFreeTaxes_thenZeroIsReturned() {
        Amount taxes = TaxPolicy.EXEMPT.applyTo(price);

        assertEquals(Amount.of(new BigDecimal("0.00")), taxes);
    }

    @Test
    public void givenPrice_whenComputingImportedTax_thenTaxValueIsCorrect() {
        Amount taxes = TaxPolicy.IMPORTED.applyTo(price);

        assertEquals(Amount.of(new BigDecimal("0.85")), taxes);
    }
}
