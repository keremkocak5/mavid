package com.kocak.kerem.linkconverter.exception;

public class LinkConverterLogNullValueException extends RuntimeException {

    // Raised when null values are tries to be passed to the database log table.
    public LinkConverterLogNullValueException() {
        super();
    }

}
