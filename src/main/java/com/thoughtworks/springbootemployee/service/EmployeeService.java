package com.thoughtworks.springbootemployee.service;
import com.thoughtworks.springbootemployee.exception.EmployeeCreateException;
import com.thoughtworks.springbootemployee.exception.EmployeeInactiveStatusException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee create(Employee employee){
        if(employee.hasInvalidAge()){
            throw new EmployeeCreateException();
        }
        return employeeRepository.addEmployee(employee);
    }
    public Employee update(Long id, Employee employee){
        if(!employee.isActive()){
            throw new EmployeeInactiveStatusException();
        }
        return employeeRepository.updateEmployee(id,employee);
    }


    public void delete(Long id) {
        Employee employeeToDelete = employeeRepository.findByEmployeeId(id);
        employeeToDelete.setActive(false);
        employeeRepository.updateEmployee(id,employeeToDelete);
    }
}
