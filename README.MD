# What is ObjectPredicates

ObjectPredicates is a small Java library that allows you to create executable predicates (conditions or rules) based on a text query. This can be useful for building simple rule-based systems where the rules are defined and dynamically managed by users of your application.

# How to build ObjectPredicates

Make sure you have Java 21 on your classpath and execute:

`./gradlew clean jar`

If you wish to install the library to your local maven, execute:

`./gradlew publishToMavenLocal`

# How to use ObjectPredicates

There is only one entrance method to the library, which is the `of` method in the class
`PredicateFactory`. It requires a query, as well as the type of object you wish to run
the query on. The result is a Predicate on that type. For example:

```java
// Create a Person object (assuming you have a Person class with appropriate fields)
Person somePerson = new Person(...);

// Define a predicate using a query and apply it to the Person object
Predicate<Person> predicate = PredicateFactory.of(
         """
          and((firstName.length > lastName.length), 
              (firstName in {"Steve", "Pete"}),
              or ((salary > 25000), (address.street == null)))
         """, Person.class);

// Test the predicate
predicate.test(somePerson);
```

# Syntax

For those interested, the full syntax of the query language can be found in the grammar which is located under `src/main/java/com/qmino/objectpredicates`. Here is a more succinct and simple explanation with some examples.

The following expressions exist:

## Basic Expressions

A basic expression is simply two operands separated by an operator. An operand can be:
- A string, which is encapsulated in " "
- A number: a collection of digits with an optional . somewhere
- A date, either in the ISO_LOCAL_DATE or ISO_LOCAL_DATE_TIME format: e.g.`2018-05-25` or `2018-05-25T10:00:32`
- `true` or `false`
- `null`
- A property selector, which is a path to a property by using the names of the fields. For example: `father.age` or `address.street` or `salary`.

A few examples of basic expressions are:

- ```salary > 2000```
- ```father.address.street == null```
- ```firstName ilike "John"```
- ```firstName.length > lastName.length```

During resolution, an fieldName is converted to a `getter` in case of a class, or kept (in case of a record). Supposed in our examples, `Person.class` has an attribute `father` of the same type, and an attribute `address` which is an `Address` record. Then following expression: ```father.address.street == null```
will be translated into ```getFather().getAddress().street()``` for retrieval of the value.

## Composite Expression

The library supports boolean operators in a prefix notation:

- `and`: All conditions must be true.
- `or`: At least one of the conditions must be true.
- `not`: Inverts the condition.

Examples:
- `and((salary > 50000), (age < 25))`
- `or((salary < 5000), (salary > 25000), (age == 23))`
- `not (firstName like "%john%")`

Many additional examples can be found in the unittests of the library under `test/java/com/qmino.objectpredicates/PredicateFactoryTest`

## Operators

- **Equality and Inequality:** (for all types)
    - `==` (equal to)
    - `!=` (not equal to)

- **String Matching:** 
    - `like` (case-sensitive string match)
    - `ilike` (case-insensitive string match)

- **Numerical and Date Comparisons:**
    - `>` (greater than)
    - `>=` (greater than or equal to)
    - `<` (less than)
    - `<=` (less than or equal to)

Finally, the `in` operator is supported for Strings and numbers, and requires the
second operand to be in the `{ }` form. For example:

```firstName in { "John", "Steve" } ``` or ```age in { 20, 30, 50 } ```

## Numeric types

During evaluation of a query, all numeric types (`int/Integer`, `long/Long`, `float/Float`, `double/Double`, `BigInteger` and `BigDecimal`) are converted to `BigDecimal` before they are compared, regardless of the type in the object. Feel free to mix & match numeric types therefore.

Consider type:

```record Person(int age, double heightInMeters, double weightInKilos, BigDecimal salary)```

You can write `heightInMeters < age` or `salary > 10000` without worrying about the underlying type.

## Null Handling

The library allows you to check for null values explicitly in your predicates. For example:

- ```father == null```
- ```address.neighbourhood == NULL```

The library allows you to write predicates on nested properties, such as `father.age > 50`. If an intermediate property (e.g., `father`) is `null`, the immediate predicate-part where the null reference occurs evaluates to `false` by default. In combinations, the rest of the predicate is evaluated as expected. for example, `not(father.age > 50)` evaluates to `true`. 

Often this is an intuitive default, such as above: if the `father` is null, he is not older than 50. However, this is not
always the case depending on your perspective. For example, ``father.address == null`` would also evaluate to `false`.

If you do not like this default behaviour, you can disable it by setting a flag in the `of` method, in which case a 
`PredicateEvaluationException` is thrown whenever a null-reference occurs evaluating the predicate.

Example:
```java
Predicate<Person> predicate = PredicateFactory.of("father.age > 50", Person.class);
// Safe handling of nulls:
predicate.test(personWithoutFather); // returns false

Predicate<Person> strictPredicate = PredicateFactory.of("father.age > 50", Person.class, false);
// Strict handling:
strictPredicate.test(personWithoutFather); // throws PredicateEvaluationException
```

## Lazy evaluation

Just like with many programming languages, the children of `and` and `or` predicates are evaluated lazily. As soon as
one child evaluates to `true`(in the case of `or`) or `false` (in the case of `and`), evaluation of the other sub-expressions 
is skipped. 

Note that this may result in a null handling issue not being thrown!

## Case sensitivity

Operator names are supported both in lowercase and uppercase notation, but not mixed:

- `AND / and`
- `OR / or`
- `NOT / not / !`
- `LIKE / like`
- `ILIKE / ilike`
- `TRUE / true`
- `FALSE / false`
- `NULL / null`

`||` and `&&` are not supported, as they look weird in combination with the prefix notation used by this library.  

## Error Handling

If an invalid operator is used in a query, the library will throw a `PredicateConstructionException`. For example:

- ```salary like 75000``` will result in an exception because the `like` operator is not valid for numbers.
- ```firstName > "John"``` will throw an exception because comparison operators like `>` are not valid for strings.

Ensure that your queries follow the correct syntax and operator usage as outlined in the documentation to avoid exceptions.


# Contributing

Contributions are welcome! Please feel free to submit a pull request.

# License

PredicateFactory is open-source software licensed under the MIT License.
