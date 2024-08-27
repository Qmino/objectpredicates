package com.qmino.objectpredicates.evaluator;

import com.qmino.objectpredicates.PredicateConstructionException;

import java.math.BigDecimal;

/**
 * @author Yves Vandewoude
 */
class NumericValueExtractor<IN> extends ValueExtractor<IN, BigDecimal> {

    NumericValueExtractor(Class<IN> sourceClass, String fieldName) {
        super(sourceClass, BigDecimal.class, fieldName);
    }

    @Override
    protected void validateExtractedTypes(Class<IN> sourceClass, Class<BigDecimal> targetClass, String fieldName) {
        if (! Util.isKnownNumericType(actualOutClass)) {
            throw new PredicateConstructionException("Field " + fieldName + " is not of a know numeric type: int/Integer, long/Long, " +
                    "float/Float, double/Double or BigDecimal");
        }
    }

    @Override
    protected BigDecimal convert(Object currentValue) {
        return new BigDecimal("" + currentValue);
    }
}
