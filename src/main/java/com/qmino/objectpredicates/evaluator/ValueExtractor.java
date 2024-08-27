package com.qmino.objectpredicates.evaluator;

import com.qmino.objectpredicates.PredicateEvaluationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Yves Vandewoude
 */
class ValueExtractor<IN, OUT> implements Function<IN, OUT> {

    protected final List<Method> methods = new ArrayList<>();
    protected Class<? extends OUT> actualOutClass;
    private final String fieldName;

    public ValueExtractor(Class<IN> sourceClass, Class<OUT> targetClass, String fieldName) {
        this.fieldName = fieldName;
        try {
            getActualOutClass(sourceClass, fieldName);
            validateExtractedTypes(sourceClass, targetClass, fieldName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Field " + fieldName + " not found in class " + sourceClass.getName(), e);
        }
    }

    protected void getActualOutClass(Class<IN> sourceClass, String fieldName) throws NoSuchMethodException {
        String[] fieldsNames = fieldName.split("\\.");
        Class currentClass = sourceClass;
        for (String field : fieldsNames) {
            String methodName = currentClass.isRecord() ? field : "get" + field.substring(0, 1).toUpperCase()
                    + field.substring(1);
            if (field.equals("length") && String.class.equals(currentClass)) {
                methodName = "length";
            }
            Method method = null;
            try {
                method = currentClass.getDeclaredMethod(methodName);
                this.methods.add(method);
            } catch (NoSuchMethodException nsme) {
                // we can try the "is" syntax if currentClass is not a record
                if (! currentClass.isRecord()) {
                    String newMethodName = "is" + field.substring(0, 1).toUpperCase() + field.substring(1);
                    method = currentClass.getDeclaredMethod(newMethodName);
                    this.methods.add(method);
                }
            }
            currentClass = method.getReturnType();
        }
        actualOutClass = currentClass;
    }

    protected Class<? extends OUT> getActualOutClass() {
        return actualOutClass;
    }

    protected void validateExtractedTypes(Class<IN> sourceClass, Class<OUT> targetClass, String fieldName) {
        if (!targetClass.isAssignableFrom(actualOutClass) && !(Util.isKnownNumericType(actualOutClass) && targetClass == Object.class) &&
        ! (Util.isKnownBooleanType(actualOutClass) && (targetClass == Object.class || targetClass == Boolean.class))) {
            throw new IllegalArgumentException("Field " + fieldName + " in class " + sourceClass.getName()
                    + " is of type " + actualOutClass.getName() + " but expected type is " + targetClass.getName());
        }
    }

    @Override
    public OUT apply(IN o) {
        try {
            Object currentValue = o;
            for (Method method : methods) {
                if (currentValue == null) {
                    throw new PredicateEvaluationException("Encountered intermediate null value while resolving for " + fieldName);
                }
                currentValue = method.invoke(currentValue);
            }
            return convert(currentValue);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    protected OUT convert(Object currentValue) {
        //noinspection unchecked
        return (OUT) currentValue;
    }
}
