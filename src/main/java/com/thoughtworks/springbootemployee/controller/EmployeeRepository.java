package com.thoughtworks.springbootemployee.controller;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private static final List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(1, "Daphne", 23, "Female", 1000));
        employees.add(new Employee(2, "Red", 25, "Male", 1500));
    }

    public List<Employee> listAll() {
        return employees;
    }

    public Employee findById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

//    public Employee addEmployee(Employee employee) {
//        return employee.add(new Employee(generateId(), "Bob", 35, "Male", 60000));
//    }

    public long generateId(){
        Employee employee1 = employees.stream()
                .max(Comparator.comparingLong(employee -> employee.getId()))
                .orElseThrow(EmployeeNotFoundException::new);
        return employee1.getId()+1;
    }
}
