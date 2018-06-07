package com.araknoid.goods;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxPolicyTest {

    private BigDecimal price = new BigDecimal("16.99");

    @Test
    public void givenPrice_whenComputingStandardTax_thenTaxValueIsCorrect() {
        BigDecimal taxes = TaxPolicy.STANDARD.applyTo(price);

        assertEquals(new BigDecimal("1.70"), taxes);
    }

    @Test
    public void givenPrice_whenComputingTaxFreeTaxes_thenZeroIsReturned() {
        BigDecimal taxes = TaxPolicy.EXEMPT.applyTo(price);

        assertEquals(new BigDecimal("0.00"), taxes);
    }
}
