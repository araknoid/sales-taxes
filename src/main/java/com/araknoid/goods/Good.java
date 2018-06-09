package com.araknoid.goods;

import com.araknoid.numbers.Amount;
import com.araknoid.print.Printable;

public interface Good extends Printable {

    public Amount getPrice();

    public Amount getTaxes();

    public Amount getPriceWithTaxes();

    public String getGoodDescription();

    @Override
    default String print() {
        return String.format("%s: %s", getGoodDescription(), getPriceWithTaxes().print());
    }

}
