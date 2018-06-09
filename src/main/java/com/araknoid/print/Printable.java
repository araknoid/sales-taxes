package com.araknoid.print;

/**
 * The <code>Printable</code> interface must be implemented by any class that needs to be printed.
 */
public interface Printable {

    /**
     * Prints the object, returning a string as representation
     *
     * @return a {@link String} representing the object
     */
    public String print();
}
