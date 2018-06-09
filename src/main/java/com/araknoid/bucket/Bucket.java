package com.araknoid.bucket;

import com.araknoid.numbers.Amount;
import com.araknoid.numbers.operations.Multiplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Bucket {
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

    public String printReceipt() {
        String bucketListReceipt = bucketList.stream()
                .map(BucketItem::print)
                .collect(Collectors.joining("\n"));

        Amount totalPriceWithTaxes = bucketList.stream()
                .map(item -> Multiplication.of(item.getPriceWithTaxes(), item.getQuantity()))
                .map(Multiplication::asAmount)
                .reduce(Amount.ZERO, Amount::add);

        Amount totalTaxes = bucketList.stream()
                .map(item -> Multiplication.of(item.getTaxes(), item.getQuantity()))
                .map(Multiplication::asAmount)
                .reduce(Amount.ZERO, Amount::add);

        String receipt = bucketListReceipt + "\n"
                + "Sales Taxes: " + totalTaxes.print() + "\n"
                + "Total: " + totalPriceWithTaxes.print();

        return receipt;
    }
}
