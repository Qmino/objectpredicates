package com.qmino.objectpredicates.evaluator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Yves Vandewoude
 */
class NumberExpression<T> extends ObjectPredicate<T> {

    private final Function<T, BigDecimal> firstOperand;
    private final Operator operator;
    private final Function<T, BigDecimal> secondOperand;
    private final String firstLiteral;
    private final String secondLiteral;

    public NumberExpression(String first, Operator operator, String second, Class<T> tClass) {
        this.firstLiteral = first;
        this.secondLiteral = second;
        operator.verifyValidNumeric();
        char firstChar = first.trim().charAt(0);
        if (firstChar >= '0' && firstChar <= '9') {
            firstOperand = o -> new BigDecimal(first);
        } else {
            firstOperand = new NumericValueExtractor<>(tClass, first);
        }
        this.operator = operator;
        firstChar = second.trim().charAt(0);
        if (firstChar >= '0' && firstChar <= '9') {
            secondOperand = o -> new BigDecimal(second);
        } else {
            secondOperand = new NumericValueExtractor<>(tClass, second);
        }
    }

    @Override
    public boolean unsafeTest(T object) {
        return Util.compareBigDecimals(firstOperand.apply(object), operator, secondOperand.apply(object));
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
