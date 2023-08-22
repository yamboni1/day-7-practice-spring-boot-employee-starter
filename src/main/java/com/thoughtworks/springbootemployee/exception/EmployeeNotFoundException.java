package com.thoughtworks.springbootemployee.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public String EmployeeNotFoundException(){
        return "Employee not found";
    }
}
