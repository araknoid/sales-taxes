package com.araknoid.goods;

public interface Good {

    public Amount getPrice();

    public Amount getTaxes();

    public Amount getPriceWithTaxes();

    public String getGoodDescription();

    /**
     * Prints the {@link Good} in the form of a formatted {@link String},
     * that will contain the description and the price with taxes of the good
     *
     * @return the {@link String} representing the {@link Good}
     */
    default String print() {
        return String.format("%s: %s", getGoodDescription(), getPriceWithTaxes().print());
    }

}
