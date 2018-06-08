package com.araknoid.goods;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AmountTest {

    @Test(expected = IllegalArgumentException.class)
    public void givenNegativeValue_whenBuildingAmount_thenExceptionIsThrown() {
        Amount.of(new BigDecimal("-1"));
    }

    @Test
    public void givenAmount_whenConvertingToBigDecimal_thenExtractedValueIsCorrect() {
        Amount amount = Amount.of(BigDecimal.TEN);

        BigDecimal amountValue = amount.asBigDecimal();

        assertEquals(BigDecimal.TEN, amountValue);
    }

    @Test
    public void givenAmount_whenComparingToItself_thenEqual() {
        Amount amount = Amount.of(new BigDecimal("11.125"));

        assertTrue(amount.equals(amount));
        assertTrue(amount.hashCode() == amount.hashCode());
    }

    @Test
    public void givenTwoAmount_whenComparing_thenAreEqual() {
        Amount amount1 = Amount.of(BigDecimal.TEN);
        Amount amount2 = Amount.of(BigDecimal.TEN);

        assertTrue(amount1.equals(amount2) && amount2.equals(amount1));
        assertTrue(amount1.hashCode() == amount2.hashCode());
    }

    @Test
    public void givenAmountAndObject_whenComparing_thenAreNotEqual() {
        Amount amount = Amount.of(new BigDecimal("5000.05"));

        assertFalse(amount.equals(new Object()) || new Object().equals(amount));
        assertTrue(amount.hashCode() != new Object().hashCode());
    }

    @Test
    public void givenAmountAndNull_whenComparing_thenAreNotEqual() {
        assertNotEquals(Amount.of(BigDecimal.TEN), null);
    }

    @Test
    public void givenAmount_whenRoundingToNearestFiveCents_thenRoundedToUnit() {
        Amount amount = Amount.of(new BigDecimal("16.99"));

        Amount roundedAmount = amount.roundToNearestFiveCents();

        assertEquals(Amount.of(new BigDecimal("17.00")), roundedAmount);
    }

    @Test
    public void givenAmount_whenRoundingToNearestFiveCents_thenRoundedToFiveCents() {
        Amount amount = Amount.of(new BigDecimal("16.91"));

        Amount roundedAmount = amount.roundToNearestFiveCents();

        assertEquals(Amount.of(new BigDecimal("16.95")), roundedAmount);
    }

    @Test
    public void whenPrintingAmount_thenOutputIsFormattedWithTwoDecimals() {
        Amount amount = Amount.of(new BigDecimal("16.95000"));

        assertEquals("16.95", amount.print());
    }

    @Test
    public void whenPrintingAmount_thenOutputIsFormattedWithTwoDecimalsWithZeroPadding() {
        Amount amount = Amount.of(new BigDecimal("11.1"));

        assertEquals("11.10", amount.print());
    }

    @Test
    public void givenTwoAmount_whenAddingOneToAnother_thenReturnAmountRepresentingTheSum() {
        Amount amount1 = Amount.of(new BigDecimal("19.9"));
        Amount amount2 = Amount.of(new BigDecimal("21.1"));

        Amount sum = amount1.add(amount2);

        assertEquals(Amount.of(new BigDecimal("41.0")), sum);
    }

    @Test
    public void whenAddingNullToAmount_thenSameAmountIsReturned() {
        Amount amount = Amount.of(new BigDecimal("44.22"));

        Amount sum = amount.add(null);

        assertSame(amount, sum);
    }
}
