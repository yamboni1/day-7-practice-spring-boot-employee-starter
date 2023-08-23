package com.thoughtworks.springbootemployee.model;

//TODO: remove extra line spaces
public class Employee { //TODO: can rename variable names to meaningful names
    private final Long id;
    private final String name;
    private Integer age;
    private final String gender;
    private Integer salary;
    private final Long companyId;

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
}
