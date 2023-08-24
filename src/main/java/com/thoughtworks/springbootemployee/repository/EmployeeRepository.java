package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private static final List<Employee> employees = new ArrayList<>();

    private static final String FEMALE = "Female";

    private static final String MALE = "Male";

    static {
        employees.add(new Employee(1L, "Col", 23, FEMALE, 1000, 1, true));
        employees.add(new Employee(2L, "Red", 25, MALE, 1500, 1, true));
        employees.add(new Employee(3L, "Sandra", 66, FEMALE, 788, 2, true));
        employees.add(new Employee(4L, "Sam", 34, MALE, 4566, 3, true));
        employees.add(new Employee(5L, "Jane", 46, FEMALE, 5555, 4, true));
    }

    public List<Employee> listAllEmployees() {
        return employees;
    }

    public Employee findByEmployeeId(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findByEmployeeGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    public Employee addEmployee(Employee employee) {
        Long generatedId = generateEmployeeId();
        Employee toBeAddedEmployee = new Employee(generatedId, employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary(),employee.getCompanyId(), employee.isActive());
        employees.add(toBeAddedEmployee);
        return toBeAddedEmployee;

    }

    public Long generateEmployeeId() {
        return employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0L) + 1;
    }

    public void deleteEmployee(Employee employee) {
        employees.remove(employee); //TODO question about this
    }

    public List<Employee> listByPage(Long pageNumber, Long pageSize) {
        return employees.stream()
                .skip((pageNumber -1)* pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> findByCompanyId(Long employeesCompanyId) {
        return employees.stream()
                .filter(employee -> employee.getCompanyId().equals(employeesCompanyId))
                .collect(Collectors.toList());
    }

    public void cleanAll() {
        employees.clear();

    }

    public Employee updateEmployee(Long id, Employee updateEmployee) {
        Employee employee = this.findByEmployeeId(id);
        updateEmployee.setAge(employee.getAge());
        updateEmployee.setSalary(updateEmployee.getSalary());
        return employee;

    }
}
