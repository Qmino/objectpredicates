package com.qmino.objectpredicates;

/**
 * Exception thrown when a predicate can not be constructed due to an invalid query.
 * @author Yves Vandewoude
 */
public class PredicateConstructionException extends RuntimeException {

    public PredicateConstructionException() {
    }

    public PredicateConstructionException(String message) {
        super(message);
    }

    public PredicateConstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PredicateConstructionException(Throwable cause) {
        super(cause);
    }

    public PredicateConstructionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
