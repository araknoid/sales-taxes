package com.araknoid.bucket;

import com.araknoid.goods.Amount;
import com.araknoid.goods.ImportedGood;
import com.araknoid.goods.TaxedGood;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BucketItemTest {

    @Test
    public void whenPrintingBucketItemWithStandardGood_thenPrintedCorrectly() {
        BucketItem bucketItem = BucketItem.of(
                Quantity.ONE,
                TaxedGood.standard("music CD", Amount.of(new BigDecimal("14.99")))
        );

        assertEquals("1 music CD: 16.49", bucketItem.print());
    }

    @Test
    public void whenPrintingBucketItemWithImportedGood_thenPrintedCorrectly() {
        BucketItem bucketItem = BucketItem.of(
                Quantity.ONE,
                new ImportedGood(TaxedGood.exempt("box of chocolates", Amount.of(new BigDecimal("10.00"))))
        );

        assertEquals("1 imported box of chocolates: 10.50", bucketItem.print());
    }
}
