package com.araknoid.bucket;

import com.araknoid.numbers.Amount;
import com.araknoid.print.Printable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Bucket implements Printable {

    private final List<BucketItem> bucketList;

    private Bucket(List<BucketItem> bucketList) {
        this.bucketList = Collections.unmodifiableList(bucketList);
    }

    public static Bucket of(List<BucketItem> bucketList) {
        return new Bucket(Objects.requireNonNull(bucketList));
    }

    /**
     * Add a {@link BucketItem} to the {@link Bucket} and return a new instance of the {@link Bucket}
     * If the <code>bucketItem</code> is <code>null</code>, this method will return the instance of the current bucket
     *
     * @param bucketItem the bucket item to be added to the bucket
     * @return new instance of the {@link Bucket} containing all the previous items plus the provided item
     */
    public Bucket add(BucketItem bucketItem) {

        if (bucketItem == null) {
            return this;
        }

        ArrayList<BucketItem> newBucketItemsList = new ArrayList<>(bucketList);
        newBucketItemsList.add(bucketItem);

        return new Bucket(newBucketItemsList);
    }

    @Override
    public String print() {
        String bucketListReceipt = getBucketItemListPrinted();

        Amount bucketTotalPriceWithTaxes = computeTotalFor(BucketItem::getTotalItemPriceWithTaxes);

        Amount bucketTotalTaxes = computeTotalFor(BucketItem::getTotalItemTaxes);

        return getReceipt(bucketListReceipt, bucketTotalPriceWithTaxes, bucketTotalTaxes);
    }

    private String getBucketItemListPrinted() {
        return bucketList.stream()
                .map(BucketItem::print)
                .collect(Collectors.joining("\n"));
    }

    private String getReceipt(String bucketListReceipt, Amount bucketTotalPriceWithTaxes, Amount bucketTotalTaxes) {
        return String.format("%s\n" + "Sales Taxes: %s\n" + "Total: %s",
                bucketListReceipt, bucketTotalTaxes.print(), bucketTotalPriceWithTaxes.print());
    }

    private Amount computeTotalFor(Function<BucketItem, Amount> mappingFunction) {
        return bucketList.stream()
                .map(mappingFunction)
                .reduce(Amount.ZERO, Amount::add);
    }
}
