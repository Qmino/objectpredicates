package com.qmino.objectpredicates;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public class Person {

    private final BigDecimal salary;
    private final Address address;
    private final int age;
    private final String firstName;
    private String lastName;
    private Double weightInKilos;
    private float heightInMeters;
    private Person father;
    private BigInteger totalSavings;
    private LocalDate dateOfMarriage;
    private boolean retired;

    public Person(BigDecimal salary, Address address,
                  int age, String firstName,
                  String lastName, double weightInKilos,
                  float heightInMeters, Person father,
                  BigInteger totalSavings,
                  LocalDate dateOfMarriage,
                  boolean retired) {
        this.salary = salary;
        this.address = address;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weightInKilos = weightInKilos;
        this.heightInMeters = heightInMeters;
        this.father = father;
        this.totalSavings = totalSavings;
        this.dateOfMarriage = dateOfMarriage;
        this.retired = retired;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Address getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getWeightInKilos() {
        return weightInKilos;
    }

    public float getHeightInMeters() {
        return heightInMeters;
    }

    public Person getFather() {
        return father;
    }

    public BigInteger getTotalSavings() {
        return totalSavings;
    }

    public LocalDate getDateOfMarriage() {
        return dateOfMarriage;
    }

    public boolean isRetired() {
        return retired;
    }
}
