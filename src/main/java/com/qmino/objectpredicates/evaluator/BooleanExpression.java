package com.qmino.objectpredicates.evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Yves Vandewoude
 */
class BooleanExpression<T> extends ObjectPredicate<T> {

    private final Function<T, Boolean> firstOperand;
    private final Operator operator;
    private final Function<T, Boolean> secondOperand;
    private final String firstLiteral;
    private final String secondLiteral;

    public BooleanExpression(String first, Operator operator, String second, Class<T> tClass) {
        this.firstLiteral = first;
        this.secondLiteral = second;
        operator.verifyValidUnknownTypes();
        if ("true".equalsIgnoreCase(first) || "false".equalsIgnoreCase(first)) {
            boolean response = Boolean.parseBoolean(first);
            firstOperand = o -> response;
        } else {
            firstOperand = new ValueExtractor<>(tClass, Boolean.class, first);
        }
        this.operator = operator;
        if ("true".equalsIgnoreCase(second) || "false".equalsIgnoreCase(second)) {
            boolean response = Boolean.parseBoolean(second);
            secondOperand = o -> response;
        } else {
            secondOperand = new ValueExtractor<>(tClass, Boolean.class, first);
        }
    }

    @Override
    protected List<ObjectPredicate<T>> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public boolean unsafeTest(T object) {
        return Util.compareBooleans(firstOperand.apply(object), operator, secondOperand.apply(object));
    }

    @Override
    public String toString() {
        return firstLiteral + " " + operator.getSymbol() + " " + secondLiteral;
    }
}
