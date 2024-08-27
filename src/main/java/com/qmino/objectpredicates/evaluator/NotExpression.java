package com.qmino.objectpredicates.evaluator;

import java.util.List;

/**
 * An expression that evaluates to true if its only child evaluates to false.
 * @param <T> the type of object to evaluate
 * @author Yves Vandewoude
 */
public class NotExpression<T> extends ObjectPredicate<T> {

    private final List<ObjectPredicate<T>> children;

    NotExpression(List<ObjectPredicate<T>> children) {
        this.children = children;
    }

    @Override
    protected List<ObjectPredicate<T>> getChildren() {
        return children;
    }

    @Override
    public boolean unsafeTest(T object) {
        return ! this.children.getFirst().test(object);
    }

    @Override
    public String toString() {
        return "not (" + children.getFirst().toString() + ")";
    }
}
