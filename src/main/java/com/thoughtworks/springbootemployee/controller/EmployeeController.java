package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/employees")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> listAll(){
        return employeeService.listAllEmployees();
    }

    @GetMapping(path = "/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeService.findByEmployeeId(id);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> findByGender (@RequestParam String gender){
        return employeeService.findByEmployeeGender(gender);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee){
        return employeeService.update(id, newEmployee);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id){
        Employee employeeById = employeeService.findByEmployeeId(id);
        if(employeeById == null){
            throw new EmployeeNotFoundException();
        }
        employeeService.delete(employeeById.getId());
    }
    @GetMapping(params = {"pageNumber","pageSize"})
    public List<Employee> listByPage(@RequestParam Long pageNumber, @RequestParam Long pageSize) {
        return employeeService.listByPage(pageNumber, pageSize);
    }


}
