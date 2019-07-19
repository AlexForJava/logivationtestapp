package com.gmail.chernii.exceptions;

import org.apache.log4j.Logger;

public class ProductNotFoundException extends RuntimeException {
    private static final Logger LOG = Logger.getLogger(ProductNotFoundException.class);

    public ProductNotFoundException() {
        super("Product is not found");
        LOG.info(getMessage(), this);
    }
}
