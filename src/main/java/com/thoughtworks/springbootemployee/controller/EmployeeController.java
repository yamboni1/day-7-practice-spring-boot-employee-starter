package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/employees")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping
    public List<Employee> listAll(){
        return employeeRepository.listAll();
    }

    @GetMapping(path = "/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> findByGender (@RequestParam String gender){
        return employeeRepository.findByGender(gender);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee){
        Employee employeeById = employeeRepository.findById(id);
        employeeById.setAge(newEmployee.getAge());
        employeeById.setSalary(newEmployee.getSalary());
        return employeeById;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id){
        Employee employeeById = employeeRepository.findById(id);
        employeeRepository.deleteEmployee(employeeById);
    }
    @GetMapping(params = {"pageNumber","pageSize"})
    public List<Employee> listByPage(@RequestParam Long pageNumber, @RequestParam Long pageSize){
        return employeeRepository.listByPage(pageNumber, pageSize);
    }


}
