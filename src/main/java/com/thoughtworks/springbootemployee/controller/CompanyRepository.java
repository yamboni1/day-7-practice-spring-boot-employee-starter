package com.thoughtworks.springbootemployee.controller;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CompanyRepository {
    private static final List<Company> companies = new ArrayList<>();
    static{
        companies.add(new Company(1L, "Spring"));
        companies.add(new Company(2L, "OOCL"));
        companies.add(new Company(3L, "Google"));
        companies.add(new Company(4L, "COSCO"));
        companies.add(new Company(5L, "ITA"));
    }


    public List<Company> listAllCompanies() {
        return companies;
    }

    public Company findById(Long id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(CompanyNotFoundException::new);
    }
}
