package com.thoughtworks.springbootemployee.exception;

public class EmployeeInactiveStatusException extends RuntimeException {
    public EmployeeInactiveStatusException() {
        super("Employee is inactive");
    }
}
