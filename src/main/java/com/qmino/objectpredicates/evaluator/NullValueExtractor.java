package com.qmino.objectpredicates.evaluator;

/**
 * @author Yves Vandewoude
 */
class NullValueExtractor<IN> extends ValueExtractor<IN, Object> {

    NullValueExtractor(Class<IN> sourceClass, String fieldName) {
        super(sourceClass, Object.class, fieldName);
    }

    @Override
    protected void validateExtractedTypes(Class<IN> sourceClass, Class<Object> targetClass, String fieldName) {
    }

    @Override
    protected Object convert(Object currentValue) {
        return null;
    }

    @Override
    protected void getActualOutClass(Class<IN> sourceClass, String fieldName) {
        this.actualOutClass = Object.class;
    }
}
