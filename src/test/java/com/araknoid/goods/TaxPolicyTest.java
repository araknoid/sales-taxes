package com.araknoid.goods;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxPolicyTest {

    @Test
    public void givenPrice_whenComputingStandardTax_thenTaxValueIsCorrect() {
        BigDecimal price = new BigDecimal("16.99");

        BigDecimal taxes = TaxPolicy.STANDARD.applyTo(price);

        assertEquals(new BigDecimal("1.70"), taxes);
    }

    @Test
    public void givenPrice_whenComputingTaxFreeTaxes_thenZeroIsReturned() {
        BigDecimal price = new BigDecimal("16.99");

        BigDecimal taxes = TaxPolicy.EXEMPT.applyTo(price);

        assertEquals(new BigDecimal("0.00"), taxes);
    }
}
