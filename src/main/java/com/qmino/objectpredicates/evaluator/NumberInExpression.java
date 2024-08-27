package com.qmino.objectpredicates.evaluator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Yves Vandewoude
 */
public class NumberInExpression<T> extends ObjectPredicate<T> {

    private List<BigDecimal> values;
    private final Function<T, BigDecimal> operand;
    private final String firstLiteral;

    public NumberInExpression(String operand, List<BigDecimal> children, Class<T> clazz) {
        firstLiteral = operand;
        values = children;
        char firstChar = operand.trim().charAt(0);
        if (firstChar >= '0' && firstChar <= '9') {
            this.operand = o -> new BigDecimal(operand);
        } else {
            this.operand = new NumericValueExtractor<>(clazz, operand);
        }
    }

    @Override
    public boolean unsafeTest(T t) {
        BigDecimal value = operand.apply(t);
        for (BigDecimal item: values) {
            if (value.compareTo(item) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected List<ObjectPredicate<T>> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return firstLiteral + " in {" + values.stream().map(BigDecimal::toString).collect(Collectors.joining(", ")) + "}";
    }
}
