package com.thoughtworks.springbootemployee.exception;

public class CompanyNotFoundException  extends RuntimeException{
    public String CompanyNotFoundException(){
        return "Company not found";
    }
}
