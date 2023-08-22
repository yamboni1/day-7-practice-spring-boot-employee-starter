package com.thoughtworks.springbootemployee.controller;

public class EmployeeNotFoundException extends RuntimeException {
    public String EmployeeNotFoundException(){
        return "Employee not found";
    }
}
