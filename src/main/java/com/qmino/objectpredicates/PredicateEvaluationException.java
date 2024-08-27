package com.qmino.objectpredicates;

/**
 * Exception thrown when a certain object predicate can not be evaluated, for example, due to a null reference.
 * @author Yves Vandewoude
 */
public class PredicateEvaluationException extends RuntimeException {

    public PredicateEvaluationException() {
    }

    public PredicateEvaluationException(String message) {
        super(message);
    }

    public PredicateEvaluationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PredicateEvaluationException(Throwable cause) {
        super(cause);
    }

    public PredicateEvaluationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
