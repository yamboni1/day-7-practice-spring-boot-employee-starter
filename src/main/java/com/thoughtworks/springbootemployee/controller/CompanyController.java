package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    private final CompanyService companyService;
    @Autowired
    public CompanyController(CompanyRepository companyRepository, EmployeeRepository employeeRepository, CompanyService companyService) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }
    @GetMapping
    public List<Company> listAllCompanies(){
        return companyService.listAllCompanies();
    }
    @GetMapping("/{id}")
    public Company findById(@PathVariable Long id){
        return companyService.findByCompanyId(id);
    }
    @GetMapping("/{employeesCompanyId}/employees")
    public List<Employee> getEmployeesWithCompanyId(@PathVariable Long employeesCompanyId){
        return employeeRepository.findByCompanyId(employeesCompanyId);
    }
    @GetMapping(params = {"pageNumber","pageSize"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<Company> listByPage(@RequestParam Long pageNumber, @RequestParam Long pageSize){
        return companyRepository.listByPage(pageNumber, pageSize);
    }
    @PostMapping
    public Company addCompany(@RequestBody Company company){
        return companyRepository.addCompany(company);
    }
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company newCompany){
        Company companyById = companyRepository.findByCompanyId(id);
        companyById.setName(newCompany.getName());
        return companyById;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long id){
        Company companyById = companyRepository.findByCompanyId(id);
        companyRepository.deleteCompany(companyById);
    }
}
