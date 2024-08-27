package com.qmino.objectpredicates.evaluator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Implements scenario where both ends of the binary expression are methods. The comparator extracts the value from
 * both methods and compares them with the given operator.
 * @param <T> the type on which the comparator works
 * @author Yves Vandewoude
 */
class DoubleMethodExpression<T> extends ObjectPredicate<T> {

    private ValueExtractor<T,? extends Object> firstOperand;
    private final Operator operator;
    private ValueExtractor<T, ? extends Object> secondOperand;

    private final Function<T, Boolean> evaluationMethod;
    private final String firstLiteral;
    private final String secondLiteral;

    /**
     * Constructor for the comparator. It extracts the values from the given methods and compares them with the given
     * @param first the first method expression, to be executed on objects of the given class
     * @param operator the operator to use
     * @param second the second method expression, to be executed on objects of the given class
     * @param clazz the class on which the methods are to be executed
     * @throws IllegalArgumentException if the given operator is not valid to compare both expressions.
     */
    DoubleMethodExpression(String first, Operator operator, String second, Class<T> clazz) {
        firstLiteral = first;
        secondLiteral = second;
        firstOperand = first.equalsIgnoreCase("null") ? new NullValueExtractor<>(clazz, first): new ValueExtractor<>(clazz, Object.class, first);
        this.operator = operator;
        secondOperand = second.equalsIgnoreCase("null") ? new NullValueExtractor<>(clazz, second):new ValueExtractor<>(clazz, Object.class, second);
        if (Util.isKnownNumericType(firstOperand.getActualOutClass()) && Util.isKnownNumericType(secondOperand.getActualOutClass())) {
            operator.verifyValidNumeric();
            evaluationMethod = this::evaluateNumerics;
        } else if (firstOperand.getActualOutClass().equals(String.class) && secondOperand.getActualOutClass().equals(String.class)) {
            operator.verifyValidStrings();
            evaluationMethod = this::evaluateStrings;
        } else if (Util.isKnownDateType(firstOperand.getActualOutClass()) && Util.isKnownDateType(secondOperand.getActualOutClass())) {
            firstOperand = new DateValueExtractor<>(clazz, first);
            secondOperand = new DateValueExtractor<>(clazz, second);
            operator.verifyValidDates();
            evaluationMethod = this::evaluateDates;
        } else {
            operator.verifyValidUnknownTypes();
            evaluationMethod = this::evaluateObjects;
        }
    }

    boolean evaluateNumerics(T object) {
        return Util.compareBigDecimals(new BigDecimal("" + firstOperand.apply(object)),
                operator, new BigDecimal("" + secondOperand.apply(object)));
    }

    boolean evaluateStrings(T object) {
        return Util.compareStrings(firstOperand.apply(object).toString(), operator, secondOperand.apply(object).toString());
    }

    boolean evaluateObjects(T object) {
        return switch(operator) {
            case EQUALS -> Objects.equals(firstOperand.apply(object), secondOperand.apply(object));
            case NOT_EQUALS -> ! Objects.equals(firstOperand.apply(object), secondOperand.apply(object));
            default -> false;
        };
    }

    boolean evaluateDates(T object) {
        return Util.compareDates(
                (LocalDateTime) firstOperand.apply(object),
                operator,
                (LocalDateTime) secondOperand.apply(object));
    }

    @Override
    public boolean unsafeTest(T object) {
        return evaluationMethod.apply(object);
    }

    @Override
    protected List<ObjectPredicate<T>> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return firstLiteral + " " + operator.getSymbol() + " " + secondLiteral;
    }
}
