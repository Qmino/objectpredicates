package com.qmino.objectpredicates.evaluator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Evaluator for LocalDate expressions, i.e. any expression where one of the sides is a date literal. The other side
 * can either be a date literal, or a method. In the latter case, the method is invoked on the object to get the value
 * to compare with the date literal, and the method must also return a LocalDate.
 * @param <T> The type of the object to evaluate
 * @author Yves Vandewoude
 */
class LocalDateTimeExpression<T> extends ObjectPredicate<T> {

    private final Function<T, LocalDateTime> firstOperand;
    private final Operator operator;
    private final Function<T, LocalDateTime> secondOperand;
    private final String firstLiteral;
    private final String secondLiteral;

    LocalDateTimeExpression(String first, Operator operator, String second, Class<T> clazz) {
        firstLiteral = first;
        secondLiteral = second;
        operator.verifyValidDates();
        char firstChar = first.trim().charAt(0);
        if (firstChar >= '0' && firstChar <= '9') {
            firstOperand = o -> Util.parseToLocalDateTime(first);
        } else {
            firstOperand = new DateValueExtractor<>(clazz, first);
        }
        this.operator = operator;
        firstChar = second.trim().charAt(0);
        if (firstChar >= '0' && firstChar <= '9') {
            secondOperand = o -> Util.parseToLocalDateTime(second);
        } else {
            secondOperand = new DateValueExtractor<>(clazz, second);
        }
    }

    @Override
    public boolean unsafeTest(T object) {
        return Util.compareDates(firstOperand.apply(object), operator, secondOperand.apply(object));
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
