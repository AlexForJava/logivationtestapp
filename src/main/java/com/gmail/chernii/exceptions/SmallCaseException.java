package com.gmail.chernii.exceptions;

import org.apache.log4j.Logger;

public class SmallCaseException extends RuntimeException {
    private static final Logger LOG = Logger.getLogger(SmallCaseException.class);

    public SmallCaseException() {
        super("Case is small for this order");
        LOG.info(getMessage(), this);
    }

    public SmallCaseException(String message) {
        super(message);
        LOG.info(getMessage(), this);
    }
}
