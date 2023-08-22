package com.thoughtworks.springbootemployee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;
    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @GetMapping
    public List<Company> listAllCompanies(){
        return companyRepository.listAllCompanies();
    }
    @GetMapping("/{id}")
    public Company findById(@PathVariable Long id){
        return companyRepository.findById(id);
    }
}
