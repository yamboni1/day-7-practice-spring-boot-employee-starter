package com.thoughtworks.springbootemployee.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Employee {
    private static final int MIN_VALID_AGE = 18;
    private static final int MAX_VALID_AGE = 65;
    private final Long id;
    private final String name;
    private Integer age;
    private final String gender;
    private Integer salary;
    private final Long companyId;
    private Boolean isActive;

    public Employee(long id, String name, int age, String gender, int salary, long companyId, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
        this.isActive = isActive;
    }
    @JsonCreator
    public Employee(long id, String name, int age, String gender, int salary, long companyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }


    public void setAge(Integer age) {
        this.age = age;
    }


    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public boolean hasInvalidAge() {
        return getAge() < MIN_VALID_AGE || getAge() > MAX_VALID_AGE;
    }


    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive(){
        return isActive;
    }

    public void merge(Employee employee) {
        salary = employee.getSalary();
        age = employee.getAge();
    }
}
