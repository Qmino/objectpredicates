package com.qmino.objectpredicates.evaluator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringInExpression<T> extends ObjectPredicate<T> {
    private List<String> values;
    private final Function<T, String> operand;
    private final String firstLiteral;

    public StringInExpression(String operand, List<String> children, Class<T> clazz) {
        firstLiteral = operand;
        values = children;
        if (operand.trim().startsWith("\"")) {
            String sub = operand.substring(1, operand.length() - 1);
            this.operand = o -> sub;
        } else {
            this.operand = new ValueExtractor<>(clazz, String.class, operand);
        }
    }

    @Override
    public boolean unsafeTest(T t) {
        String value = operand.apply(t);
        for (String item: values) {
            if (value.equals(item)) {
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
        return firstLiteral + " in {" +
                values.stream().map(s -> "\"" + s + "\"").collect(Collectors.joining(", ")) + "}";
    }
}
