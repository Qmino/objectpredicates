package com.qmino.objectpredicates.evaluator;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * An expression that evaluates to true if all of its children evaluate to true.
 * @param <T> the type of object to evaluate
 * @author Yves Vandewoude
 */
class AndExpression<T> extends ObjectPredicate<T> {

    private final List<ObjectPredicate<T>> children;

    AndExpression(List<ObjectPredicate<T>> children) {
        this.children = children;
    }

    @Override
    public boolean unsafeTest(T object) {
        for (Predicate<T> selector : children) {
            if (!selector.test(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected List<ObjectPredicate<T>> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "and (" + children.stream().map(ObjectPredicate::toString).collect(Collectors.joining(", ")) + ")";
    }
}
