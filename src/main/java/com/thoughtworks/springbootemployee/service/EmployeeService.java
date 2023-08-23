package com.thoughtworks.springbootemployee.service;
import com.thoughtworks.springbootemployee.exception.EmployeeCreateException;
import com.thoughtworks.springbootemployee.exception.EmployeeInactiveStatusException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
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
    public List<Employee> listAllEmployees() {
        return employeeRepository.listAllEmployees();
    }
    public Employee findByEmployeeId(Long id) {
        return employeeRepository.findByEmployeeId(id);
    }
    public List<Employee> findByEmployeeGender(String gender) {
        return employeeRepository.findByEmployeeGender(gender);
    }

    public void delete(Long id) {
        Employee employeeToDelete = employeeRepository.findByEmployeeId(id);
        employeeToDelete.setActive(false);
        employeeRepository.updateEmployee(id,employeeToDelete);
    }
    public List<Employee> listByPage(Long pageNumber, Long pageSize) {
        return employeeRepository.listByPage(pageNumber,pageSize);
    }
}
