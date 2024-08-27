package com.qmino.objectpredicates.evaluator;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * An expression that evaluates to true if any of its children evaluate to true.
 * @param <T> the type of object to evaluate
 * @author Yves Vandewoude
 */
class OrExpression<T> extends ObjectPredicate<T> {

    private final List<ObjectPredicate<T>> children;

    OrExpression(List<ObjectPredicate<T>> selectors) {
        this.children = selectors;
    }

    @Override
    public boolean unsafeTest(T object) {
        for (Predicate<T> selector : children) {
            if (selector.test(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected List<ObjectPredicate<T>> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "or (" + children.stream().map(ObjectPredicate::toString).collect(Collectors.joining(", ")) + ")";
    }
}
