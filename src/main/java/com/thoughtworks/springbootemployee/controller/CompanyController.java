package com.thoughtworks.springbootemployee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public CompanyController(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }
    @GetMapping
    public List<Company> listAllCompanies(){
        return companyRepository.listAllCompanies();
    }
    @GetMapping("/{id}")
    public Company findById(@PathVariable Long id){
        return companyRepository.findById(id);
    }
    @GetMapping("/{employeesCompanyId}/employees")
    public List<Employee> getEmployeesWithCompanyId(@PathVariable Long employeesCompanyId){
        return employeeRepository.findByCompanyId(employeesCompanyId);
    }
    @GetMapping(params = {"pageNumber","pageSize"})
    public List<Company> listByPage(@RequestParam Long pageNumber, @RequestParam Long pageSize){
        return companyRepository.listByPage(pageNumber, pageSize);
    }
}
