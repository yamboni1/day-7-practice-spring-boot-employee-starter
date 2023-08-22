package com.thoughtworks.springbootemployee.controller;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public Employee addEmployee(Employee employee) {
        Long generatedId = generateId();
        Employee toBeAddedEmployee = new Employee(generatedId, employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary());
        employees.add(toBeAddedEmployee);
        return toBeAddedEmployee;

    }

    public Long generateId() {
        return employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0L) + 1;
    }

    public void deleteEmployee(Employee employee) {
        employees.remove(employee);
    }
}
