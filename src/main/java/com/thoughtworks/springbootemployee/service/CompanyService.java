package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    public List<Company> listAllCompanies() {
        return companyRepository.listAllCompanies();
    }
    public Company findByCompanyId(Long id) {
        return companyRepository.findByCompanyId(id);
    }
    public List<Company> listByPage(Long pageNumber, Long pageSize) {
        return companyRepository.listByPage(pageNumber,pageSize);
    }
    public Company addCompany(Company company) {
        return companyRepository.addCompany(company);
    }
    public void deleteCompany(Company company) {
        companyRepository.deleteCompany(company);
    }
    public Company updateCompany(Long id, Company company){
        return companyRepository.updateCompany(id, company);

    }
}
