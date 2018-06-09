package com.araknoid.bucket;

import com.araknoid.goods.ImportedGood;
import com.araknoid.numbers.Amount;
import com.araknoid.numbers.Quantity;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.araknoid.goods.TaxedGood.exempt;
import static com.araknoid.goods.TaxedGood.standard;
import static org.junit.Assert.*;

public class BucketTest {

    private BucketItem oneBookItem = BucketItem.of(Quantity.ONE, standard("book", Amount.of(new BigDecimal("11.50"))));
    private Bucket emptyBucket = Bucket.of(Collections.emptyList());

    @Test
    public void givenEmptyBucket_whenAddingAnItemToTheBucket_thenNewBucketIsReturned() {
        Bucket updatedBucket = emptyBucket.add(oneBookItem);

        assertNotSame(emptyBucket, updatedBucket);
    }

    @Test
    public void givenBucket_whenAddingNullItemToTheBucket_thenSameBucketIsReturnedUnmodified() {
        Bucket updatedBucket = emptyBucket.add(null);

        assertSame(emptyBucket, updatedBucket);
    }

    @Test
    public void givenFilledBucket_whenAddingAnItemToTheBucket_thenNewBucketIsReturned() {
        List<BucketItem> bucketList = Arrays.asList(
                BucketItem.of(Quantity.ONE, exempt("chocolate bar", Amount.of(new BigDecimal("0.85")))),
                BucketItem.of(Quantity.ONE, standard("bottle of perfume", Amount.of(new BigDecimal("30.50"))))
        );
        Bucket bucket = Bucket.of(bucketList);

        Bucket updatedBucket = bucket.add(oneBookItem);

        assertNotSame(bucket, updatedBucket);
    }

    @Test
    public void givenInput1Bucket_whenPrintingReceipt_thenOutput1ReceiptIsReturned() {
        List<BucketItem> bucketList = Arrays.asList(
                BucketItem.of(Quantity.ONE, exempt("book", Amount.of(new BigDecimal("12.49")))),
                BucketItem.of(Quantity.ONE, standard("music CD", Amount.of(new BigDecimal("14.99")))),
                BucketItem.of(Quantity.ONE, exempt("chocolate bar", Amount.of(new BigDecimal("0.85"))))
        );
        Bucket bucket = Bucket.of(bucketList);

        String receipt = bucket.print();

        assertEquals("1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 29.83", receipt);
    }

    @Test
    public void givenInput2Bucket_whenPrintingReceipt_thenOutput2ReceiptIsReturned() {
        List<BucketItem> bucketList = Arrays.asList(
                BucketItem.of(Quantity.ONE, new ImportedGood(exempt("box of chocolates", Amount.of(new BigDecimal("10.00"))))),
                BucketItem.of(Quantity.ONE, new ImportedGood(standard("bottle of perfume", Amount.of(new BigDecimal("47.50")))))
        );
        Bucket bucket = Bucket.of(bucketList);

        String receipt = bucket.print();

        assertEquals("1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65\n" +
                "Total: 65.15", receipt);
    }


    @Test
    public void givenInput3Bucket_whenPrintingReceipt_thenOutput3ReceiptIsReturned() {
        List<BucketItem> bucketList = Arrays.asList(
                BucketItem.of(Quantity.ONE, new ImportedGood(standard("bottle of perfume", Amount.of(new BigDecimal("27.99"))))),
                BucketItem.of(Quantity.ONE, standard("bottle of perfume", Amount.of(new BigDecimal("18.99")))),
                BucketItem.of(Quantity.ONE, exempt("packet of headache pills", Amount.of(new BigDecimal("9.75")))),
                BucketItem.of(Quantity.ONE, new ImportedGood(exempt("box of chocolates", Amount.of(new BigDecimal("11.25")))))
        );
        Bucket bucket = Bucket.of(bucketList);

        String receipt = bucket.print();

        assertEquals("1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "1 imported box of chocolates: 11.85\n" +
                "Sales Taxes: 6.70\n" +
                "Total: 74.68", receipt);
    }

    @Test
    public void givenBucketWithItemWithQuantitiesGreaterThaOne_whenPrintingReceipt_thenOutputReceiptIsCorrect() {
        List<BucketItem> bucketList = Arrays.asList(
                BucketItem.of(Quantity.of(2), new ImportedGood(standard("bottle of perfume", Amount.of(new BigDecimal("27.99"))))),
                BucketItem.of(Quantity.of(2), standard("bottle of perfume", Amount.of(new BigDecimal("18.99")))),
                BucketItem.of(Quantity.ONE, exempt("packet of headache pills", Amount.of(new BigDecimal("9.75")))),
                BucketItem.of(Quantity.of(3), new ImportedGood(exempt("box of chocolates", Amount.of(new BigDecimal("11.25")))))
        );
        Bucket bucket = Bucket.of(bucketList);

        String receipt = bucket.print();

        assertEquals("2 imported bottle of perfume: 32.19\n" +
                "2 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "3 imported box of chocolates: 11.85\n" +
                "Sales Taxes: 14.00\n" +
                "Total: 151.46", receipt);
    }
}
