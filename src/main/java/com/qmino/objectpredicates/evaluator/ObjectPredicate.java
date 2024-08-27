package com.qmino.objectpredicates.evaluator;

import com.qmino.objectpredicates.PredicateEvaluationException;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Yves Vandewoude
 */
abstract class ObjectPredicate<T> implements Predicate<T> {

    private boolean ignoreIntermediateNulls = true;

    public void setIgnoreIntermediateNulls(boolean ignoreIntermediateNulls) {
        this.ignoreIntermediateNulls = ignoreIntermediateNulls;
        for (ObjectPredicate<T> child: getChildren()) {
            child.setIgnoreIntermediateNulls(ignoreIntermediateNulls);
        }
    }

    @Override
    public boolean test(T t) {
        try {
            return unsafeTest(t);
        } catch (PredicateEvaluationException pev) {
            if (ignoreIntermediateNulls && pev.getMessage().startsWith("Encountered intermediate null value")) {
                return false;
            } else {
                throw pev;
            }
        }
    }

    protected abstract boolean unsafeTest(T t);

    protected abstract List<ObjectPredicate<T>> getChildren();

    public abstract String toString();
}
