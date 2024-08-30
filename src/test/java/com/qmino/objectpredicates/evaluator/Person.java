/*
 * MIT License
 *
 * Copyright (c) [year] [fullname]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.qmino.objectpredicates.evaluator;

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
