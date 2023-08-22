package com.thoughtworks.springbootemployee.controller;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private static final List<Company> companies = new ArrayList<>();
    static{
        companies.add(new Company(1L, "Spring"));
        companies.add(new Company(2L, "OOCL"));
        companies.add(new Company(3L, "Google"));
        companies.add(new Company(4L, "COSCO"));
        companies.add(new Company(5L, "ITA"));
    }

    public static List<Company> getCompanies() {
        return companies;
    }
}
