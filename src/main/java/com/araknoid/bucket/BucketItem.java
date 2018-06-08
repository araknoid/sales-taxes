package com.araknoid.bucket;

import com.araknoid.goods.Amount;
import com.araknoid.goods.Good;

import java.util.Objects;

public class BucketItem {

    private final Quantity quantity;
    private final Good good;

    public static BucketItem of(Quantity quantity, Good good) {
        return new BucketItem(
                Objects.requireNonNull(quantity),
                Objects.requireNonNull(good)
        );
    }

    private BucketItem(Quantity quantity, Good good) {
        this.quantity = quantity;
        this.good = good;
    }

    public String print() {
        return quantity.print() + " " + good.print();
    }

    Amount getPriceWithTaxes() {
        return good.getPriceWithTaxes();
    }

    Amount getTaxes() {
        return good.getTaxes();
    }
}
