package com.qmino.objectpredicates.evaluator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Yves Vandewoude
 */
class DateValueExtractor<IN> extends ValueExtractor<IN, LocalDateTime> {

    DateValueExtractor(Class<IN> sourceClass, String fieldName) {
        super(sourceClass, LocalDateTime.class, fieldName);
    }

    @Override
    protected void validateExtractedTypes(Class<IN> sourceClass, Class<LocalDateTime> targetClass, String fieldName) {
        if (! Util.isKnownDateType(methods.getLast().getReturnType())) {
            throw new RuntimeException("Field " + fieldName + " is not of a know/supported date type: LocalDate, LocalDateTime," +
                    "java.util.Date, java.sql.Date");
        }
    }

    @Override
    protected LocalDateTime convert(Object currentValue) {
        return switch (currentValue) {
            case LocalDateTime localDateTime -> localDateTime;
            case LocalDate localDate -> convertToLocalDateTime(localDate);
            case Date date -> convertToLocalDateTime(date);
            // This can not happen, since the check was already done in the constructor.
            case null, default ->
                    throw new RuntimeException("Field is not of a know/supported date type: LocalDate, LocalDateTime," +
                            "java.util.Date, java.sql.Date");
        };

    }

    private LocalDateTime convertToLocalDateTime(LocalDate currentValue) {
        return currentValue.atStartOfDay();
    }

    private LocalDateTime convertToLocalDateTime(Date currentValue) {
        if (currentValue instanceof java.sql.Date) {
            // Convert java.sql.Date to java.time.LocalDate
            return ((java.sql.Date) currentValue).toLocalDate().atStartOfDay();
        } else {
            // Handle as a normal java.util.Date
            return currentValue.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }
    }

}
