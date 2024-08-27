package com.qmino.objectpredicates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Unittests for object predicates.
 * @author Yves Vandewoude
 */
public class PredicateFactoryTest {

    private Person son;
    private Person dad;

    @BeforeEach
    public void beforeEach() {
        Address addressInUS = new Address("Roses Road", 14, "Manhattan", "New York", "10001", Country.USA);
        Address addressInBelgium = new Address("Bloemenweg", 1, null, "Antwerpen", "2000", Country.BELGIUM);
        dad = new Person(new BigDecimal("143000.00"), addressInUS, 58, "John", "Wood", 110.8,
                1.845f, null, new BigInteger("750000"), LocalDate.of(1991, 1, 11), true);
        son = new Person(new BigDecimal("55000.00"), addressInBelgium, 32, "Steve", "Wood", 140.1,
                1.93f, dad, new BigInteger("250000"), LocalDate.of(2023, 10, 10), false);
    }

    @Test
    @DisplayName("Basic Numeric expression")
    public void testBasicNumericExpression() {
        // Bigdecimal
        Predicate<Person> predicate = PredicateFactory.of("salary > 75000", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
        predicate = PredicateFactory.of("salary < 75000", Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("salary >= 55000", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("salary <= 55000", Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("salary == 75000", Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
        predicate = PredicateFactory.of("salary != 75000", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));

        // Int
        predicate = PredicateFactory.of("age > 40", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));

        // Double
        predicate = PredicateFactory.of("weightInKilos < 120.1458", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));

        // Float
        predicate = PredicateFactory.of("heightInMeters > 1.92", Person.class);
        Assertions.assertTrue(predicate.test(son));
        Assertions.assertFalse(predicate.test(dad));

        // Float
        predicate = PredicateFactory.of("totalSavings > 500000", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));

        // number in record
        predicate = PredicateFactory.of("address.houseNumber == 14", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));

    }

    @Test
    @DisplayName("(i)like should throw exception for numerics")
    public void testInvalidOperatorForNumericExpression() {
        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("salary like 75000", Person.class));

        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("salary ilike 75000", Person.class));

        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("address > 75000", Person.class));
    }

    @Test
    @DisplayName("Test in operator for numbers")
    public void testInOperatorForNumbers() {
        Predicate<Person> predicate = PredicateFactory.of("salary in {55000, 75000}", Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("age in {15, 22, 28, 32, 47}", Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("age in {58}", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
    }

    @Test
    @DisplayName("Basic Numeric expression with brackets and various spacing")
    public void testBasicNumericExpressionWithBrackets() {
        Predicate<Person> predicate = PredicateFactory.of("(salary > 75000  )", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
        predicate = PredicateFactory.of("( salary < 75000 )", Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("(salary >= 55000)", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("  ( salary <= 55000)", Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of(" ( salary == 75000 ) ", Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
        predicate = PredicateFactory.of("(salary!=75000)", Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
    }

    @Test
    @DisplayName("Basic String expression")
    public void testBasicStringExpression() {
        Predicate<Person> predicate = PredicateFactory.of(
                """
                            firstName != "John"
                        """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("""
                    firstName != "John"
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("""
                    firstName ilike "st%"
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("""
                    firstName like "st%"
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
    }

    @Test
    @DisplayName("< > <= and >= should throw exception for Strings")
    public void testInvalidOperatorForStringExpression() {
        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("""
                        firstName > "John"
                        """, Person.class));
        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("""
                        firstName < "John"
                        """, Person.class));
        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("""
                        firstName >= "John"
                        """, Person.class));
        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("""
                        firstName <= "John"
                        """, Person.class));
    }

    @Test
    @DisplayName("Test in operator for Strings")
    public void testInOperatorForStrings() {
        Predicate<Person> predicate = PredicateFactory.of(
                """
                            firstName in {"Steve", "Pete"}
                        """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
    }

    @Test
    @DisplayName("Basic date expression")
    public void testBasicDateExpression() {
        Predicate<Person> predicate = PredicateFactory.of(
                """
                            dateOfMarriage < 2023-10-10
                        """, Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
        predicate = PredicateFactory.of("""
                    dateOfMarriage <= 2023-10-10
                """, Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("""
                    dateOfMarriage > 1991-01-11
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("""
                     dateOfMarriage >= 1991-01-11
                """, Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("""
                     dateOfMarriage == 1991-01-11
                """, Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
        predicate = PredicateFactory.of("""
                     dateOfMarriage != 1991-01-11
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
    }

    @Test
    @DisplayName("< > <= and >= should throw exception for Strings")
    public void testInvalidOperatorForDateExpression() {
        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("""
                        dateOfMarriage like 2023-10-10
                        """, Person.class));
        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("""
                        dateOfMarriage ilike 2023-10-10
                        """, Person.class));
        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("""
                        dateOfMarriage in 2023-10-10
                        """, Person.class));
        Assertions.assertThrows(
                PredicateConstructionException.class,
                () -> PredicateFactory.of("""
                        dateOfMarriage $$ 2023-10-10
                        """, Person.class));
    }

    @Test
    @DisplayName("or expression")
    public void orExpression() {
        Predicate<Person> predicate = PredicateFactory.of("""
                or (salary < 10000, age < 20)
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
        predicate = PredicateFactory.of("""
                OR (salary < 60000, age > 50)
                """, Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
    }

    @Test
    @DisplayName("and expression")
    public void andExpression() {
        Predicate<Person> predicate = PredicateFactory.of("""
                and (salary > 10000, age > 20)
                """, Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("""
                AND (salary < 60000, age > 50)
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
    }

    @Test
    @DisplayName("not expression")
    public void notExpression() {
        Predicate<Person> predicate = PredicateFactory.of("""
                not (firstName in { "John", "Steven"})
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
        predicate = PredicateFactory.of("""
                ! (lastName.length == firstName.length)
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
    }

    @Test
    @DisplayName("null literal")
    public void nullLiteral() {
        Predicate<Person> predicate = PredicateFactory.of("""
                father == null
                """, Person.class);
        Assertions.assertFalse(predicate.test(son));
        Assertions.assertTrue(predicate.test(dad));
        predicate = PredicateFactory.of("""
                father != NULL
                """, Person.class);
        Assertions.assertTrue(predicate.test(son));
        Assertions.assertFalse(predicate.test(dad));
        predicate = PredicateFactory.of("""
                address.neighbourhood == NULL
                """, Person.class);
        Assertions.assertTrue(predicate.test(son));
        Assertions.assertFalse(predicate.test(dad));
    }

    @Test
    @DisplayName("intermediate null values")
    public void intermediateNulls() {
        Predicate<Person> predicate = PredicateFactory.of("""
                father.age > 50
                """, Person.class);
        Assertions.assertTrue(predicate.test(son));
        Assertions.assertFalse(predicate.test(dad));

        predicate = PredicateFactory.of("""
                father.address.street == null
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));

        predicate = PredicateFactory.of("""
                not(father.address.street == null)
                """, Person.class);
        Assertions.assertTrue(predicate.test(dad));

        Predicate<Person> secondPredicate = PredicateFactory.of("""
                father.age > 50
                """, Person.class, false);
        Assertions.assertTrue(predicate.test(son));
        Assertions.assertThrows(PredicateEvaluationException.class, () -> secondPredicate.test(dad));
    }

    @Test
    @DisplayName("Boolean fields")
    public void booleanFields() {
        Predicate<Person> predicate = PredicateFactory.of("""
                retired == true
                """, Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));

        predicate = PredicateFactory.of("""
                father.retired == false
                """, Person.class);
        Assertions.assertFalse(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));

        predicate = PredicateFactory.of("""
                retired == retired
                """, Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertTrue(predicate.test(son));
    }

    @Test
    @DisplayName("Combination test")
    public void testCombination() {
        Predicate<Person> predicate = PredicateFactory.of("""
                and((retired == true), or ((age >= 25), not(father.address.street != null)), firstName in {"John", "Pete"})
                """, Person.class);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
        PredicateFactory.of("""
                and((retired == true), or ((age >= 25), not(father.address.street != null)), firstName in {"John", "Pete"})
                """, Person.class, false);
        Assertions.assertTrue(predicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
        Predicate<Person> finalPredicate = PredicateFactory.of("""
                and((retired == true), or (not(father.address.street != null), (age >= 25)), firstName in {"John", "Pete"})
                """, Person.class, false);
        Assertions.assertThrows(PredicateEvaluationException.class, () -> finalPredicate.test(dad));
        Assertions.assertFalse(predicate.test(son));
    }

    @Test
    @DisplayName("Generate string representation of a predicate")
    public void testToString() {
        Predicate<Person> predicate = PredicateFactory.of("""
                and((retired == true), or ((age >= 25), not(father.address.street != null)), firstName in {"John", "Pete"})
                """, Person.class);
        Assertions.assertEquals(
                "and (retired == true, or (age >= 25, not (father.address.street != null)), firstName in {\"John\", \"Pete\"})",
                predicate.toString()
        );
    }

    @Test
    @DisplayName("Different date types")
    public void testDateTypes() {
        Clock testClock = new Clock(Date.valueOf(LocalDate.of(2024,10,10)),
                LocalDateTime.of(2024,10,11,10,15,15));
        Predicate<Clock> predicate = PredicateFactory.of("""
                configureTime < alarmTime
                """, Clock.class);
        Assertions.assertTrue(predicate.test(testClock));
        predicate = PredicateFactory.of("""
                alarmTime == 2024-10-11T10:15:15
                """, Clock.class);
        Assertions.assertTrue(predicate.test(testClock));
    }
}
