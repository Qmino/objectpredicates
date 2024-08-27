package com.qmino.objectpredicates.evaluator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * @author Yves Vandewoude
 */
class Util {

    static boolean isKnownNumericType(Class<?> returnType) {
        return returnType.equals(int.class) || returnType.equals(Integer.class) ||
                returnType.equals(long.class) || returnType.equals(Long.class) ||
                returnType.equals(float.class) || returnType.equals(Float.class) ||
                returnType.equals(double.class) || returnType.equals(Double.class) ||
                returnType.equals(BigDecimal.class) || returnType.equals(BigInteger.class);
    }

    static boolean isKnownBooleanType(Class<?> clazz) {
        return clazz.equals(Boolean.class) || clazz.equals(boolean.class);
    }


    static boolean isKnownDateType(Class<?> returnType) {
        return returnType.equals(LocalDateTime.class) || returnType.equals(LocalDate.class)
                || returnType.equals(Date.class);
    }

    static LocalDateTime parseToLocalDateTime(String input) {
        try {
            LocalDate date = LocalDate.parse(input, ISO_LOCAL_DATE);
            return date.atStartOfDay();
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(input, ISO_LOCAL_DATE_TIME);
        }
    }

    static boolean compareBigDecimals(BigDecimal first, Operator operator, BigDecimal second) {
        return switch (operator) {
            case GREATER_THAN -> first.compareTo(second) > 0;
            case LESS_THAN -> first.compareTo(second) < 0;
            case GREATER_THAN_OR_EQUALS -> first.compareTo(second) >= 0;
            case LESS_THAN_OR_EQUALS -> first.compareTo(second) <= 0;
            case EQUALS -> first.compareTo(second) == 0;
            case NOT_EQUALS -> first.compareTo(second) != 0;
            default -> throw new IllegalArgumentException("Operator " + operator + " can not be used to compare numbers.");
        };
    }

    static boolean compareStrings(String first, Operator operator, String second) {
        return switch (operator) {
            case ILIKE -> like(first.toLowerCase(), second.toLowerCase());
            case LIKE -> like(first, second);
            case EQUALS -> Objects.equals(first, second);
            case NOT_EQUALS -> !Objects.equals(first, second);
            default -> false;
        };
    }

    static boolean compareDates(LocalDateTime first, Operator operator, LocalDateTime second) {
            return switch(operator) {
                case GREATER_THAN -> first.isAfter(second);
                case LESS_THAN -> first.isBefore(second);
                case GREATER_THAN_OR_EQUALS -> !first.isBefore(second);
                case LESS_THAN_OR_EQUALS -> !first.isAfter(second);
                case EQUALS -> first.isEqual(second);
                case NOT_EQUALS -> ! Objects.equals(first, second);
                default -> false;
            };
    }

    public static boolean compareBooleans(Boolean first, Operator operator, Boolean second) {
        return switch (operator) {
            case EQUALS -> Objects.equals(first, second);
            case NOT_EQUALS -> !Objects.equals(first, second);
            default -> false;
        };
    }

    static String likeExpressionToJavaRegex(String input) {
            // Step 1: Replace escaped % with a placeholder
            String placeholder = "\uE000";
            String current = input.replace("\\%", placeholder);
            // Step 2: Replace escaped _ with a placeholder
            String placeholder2 = "\uE001";
            current = current.replace("\\_", placeholder2);
            // Step 2: Escape dots
            current = current.replace(".", "\\.");
            // Step 3: Replace unescaped % with .*?
            current = current.replace("%", ".*?");
            // Step 4: Replace _ with .
            current = current.replace("_", ".");
            // Step 5: Replace the placeholder back to %
            current = current.replace(placeholder, "%");
            current = current.replace(placeholder2, "_");
            return current;
        }


    static boolean like(String first, String second) {
        return first.matches(likeExpressionToJavaRegex(second));
    }

    static boolean ilike(String first, String second) {
        return first.toLowerCase().matches(likeExpressionToJavaRegex(second.toLowerCase()));
    }
}
