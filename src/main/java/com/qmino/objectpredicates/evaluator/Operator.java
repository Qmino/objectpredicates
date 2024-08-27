package com.qmino.objectpredicates.evaluator;

import com.qmino.objectpredicates.PredicateConstructionException;

/**
 * Enum representing the different operators that can be used in a base expression.
 * @author Yves Vandewoude
 */
enum Operator {

    GREATER_THAN(">", true, false, false),
    LESS_THAN("<", true, false, false),
    EQUALS("==", true, true, true),
    NOT_EQUALS("!=", true, true, true),
    ILIKE("ilike", false, true, false),
    LIKE("like", false, true, false),
    GREATER_THAN_OR_EQUALS(">=", true, false, false),
    LESS_THAN_OR_EQUALS("<=", true, false, false);

    private final String symbol;
    private final boolean validForNumericAndDates;
    private final boolean validForStrings;
    private final boolean validForUnknownTypes;

    Operator(String symbol, boolean validForNumericAndDates, boolean validForStrings, boolean validForUnknownTypes) {
        this.symbol = symbol;
        this.validForNumericAndDates = validForNumericAndDates;
        this.validForStrings = validForStrings;
        this.validForUnknownTypes = validForUnknownTypes;
    }

    static Operator fromText(String text) {
        String input = text.toLowerCase().trim();
        for (Operator operator : Operator.values()) {
            if (operator.symbol.equals(input)) {
                return operator;
            }
        }
        return null;
    }

    /**
     * Checks if the operator is valid for numeric types. Does nothing if it is valid, throws an exception otherwise.
     * @throws PredicateConstructionException if the operator is not valid for numeric types.
     */
    void verifyValidNumeric() {
        if (!validForNumericAndDates) {
            throw new PredicateConstructionException("Operator " + this + " is not valid for numeric types");
        }
    }

    /**
     * Checks if the operator is valid for string types. Does nothing if it is valid, throws an exception otherwise.
     * @throws PredicateConstructionException if the operator is not valid for string types.
     */
    void verifyValidStrings() {
        if (!validForStrings) {
            throw new PredicateConstructionException("Operator " + this + " is not valid for String types");
        }
    }

    /**
     * Checks if the operator is valid for date types. Does nothing if it is valid, throws an exception otherwise.
     * @throws PredicateConstructionException if the operator is not valid for date types.
     */
    void verifyValidDates() {
        if (!validForNumericAndDates) {
            throw new PredicateConstructionException("Operator " + this + " is not valid for Date types");
        }
    }

    /**
     * Checks if the operator is valid for unknown types. Does nothing if it is valid, throws an exception otherwise.
     * @throws IllegalArgumentException if the operator is not valid for unknown types.
     */
    void verifyValidUnknownTypes() {
        if (!validForUnknownTypes) {
            throw new PredicateConstructionException("Operator " + this + " is not valid for unknown types");
        }
    }

    public String getSymbol() {
        return symbol;
    }
}
