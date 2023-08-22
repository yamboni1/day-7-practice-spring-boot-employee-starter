package com.thoughtworks.springbootemployee.controller;

public class CompanyNotFoundException  extends RuntimeException{
    public String CompanyNotFoundException(){
        return "Company not found";
    }
}
