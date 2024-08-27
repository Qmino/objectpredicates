package com.qmino.objectpredicates.evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Yves Vandewoude
 */
public class StringExpression<T> extends ObjectPredicate<T> {

    private final Function<T, String> firstOperand;
    private final Operator operator;
    private final Function<T, String> secondOperand;

    // Only kept if the secondOperand is a literal and the operator is LIKE. this improves performance
    // as the conversion of the like expression to a regex only needs to be done once.
    private String likeRegex;
    private final String firstLiteral;
    private final String secondLiteral;

    public StringExpression(String first, Operator operator, String second, Class<T> clazz) {
        firstLiteral = first;
        secondLiteral = second;
        operator.verifyValidStrings();
        if (first.trim().startsWith("\"")) {
            String sub = first.substring(1, first.length() - 1);
            firstOperand = o -> sub;
        } else {
            firstOperand = new ValueExtractor<>(clazz, String.class, first);
        }
        this.operator = operator;
        if (second.trim().startsWith("\"")) {
            String sub = second.substring(1, second.length() - 1);
            secondOperand = o -> sub;
            if (operator == Operator.LIKE) {
                likeRegex = Util.likeExpressionToJavaRegex(sub);
            }
        } else {
            secondOperand = new ValueExtractor<>(clazz, String.class, second);
        }
    }

    @Override
    public boolean unsafeTest(T object) {
        return switch(operator) {
            case ILIKE -> ilike(firstOperand.apply(object), secondOperand.apply(object));
            case LIKE -> like(firstOperand.apply(object), secondOperand.apply(object));
            case EQUALS -> Objects.equals(firstOperand.apply(object), secondOperand.apply(object));
            case NOT_EQUALS -> ! Objects.equals(firstOperand.apply(object), secondOperand.apply(object));
            default -> false;
        };
    }

    private boolean ilike(String first, String second) {
        return like(first.toLowerCase(), second.toLowerCase());
    }

    private boolean like(String first, String second) {
       if (likeRegex != null) {
           return first.matches(likeRegex);
       } else {
           return Util.like(first, second);
       }
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
