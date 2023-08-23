package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeCreateException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    private EmployeeService employeeService;
    private EmployeeRepository mockedEmployeeRepository;

    @BeforeEach
    void setUp() {
        mockedEmployeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(mockedEmployeeRepository);

    }

    @Test
    void should_return_created_employee_when_create_given_employee_service_and_employee_with_valid_age() {
    //given
        Employee alice = new Employee(1L,"Alice", 24, "Female", 9000, 1L);
        Employee savedEmployee = new Employee(1L,"Alice", 24, "Female", 9000, 1L);
        when(mockedEmployeeRepository.addEmployee(alice)).thenReturn(savedEmployee);
        //when
        Employee employeeResponse = employeeService.create(alice);
     
     //then
        assertEquals(savedEmployee.getId(),employeeResponse.getId());
        assertEquals("Alice",employeeResponse.getName());
        assertEquals(24,employeeResponse.getAge());
        assertEquals("Female",employeeResponse.getGender());
        assertEquals(9000,employeeResponse.getSalary());
        assertEquals(1L,employeeResponse.getCompanyId());
    }

    @Test
    void should_throw_exception_when_create_given_employee_service_and_whose_age_is_less_than_18() {
    //given
     Employee employee = new Employee(1L,"Alice", 17, "Female", 9000, 1L);
     //when
     EmployeeCreateException employeeCreateException = assertThrows(EmployeeCreateException.class, ()->
             employeeService.create(employee));
     assertEquals("Employee must be 18~65 years old", employeeCreateException.getMessage());

    }
    @Test
    void should_throw_exception_when_create_given_employee_service_and_employee_age_greater_than_65() {
    //given
        Employee employee = new Employee(1L,"Alice", 66, "Female", 9000, 1L);

        //when
        EmployeeCreateException employeeCreateException = assertThrows(EmployeeCreateException.class, ()->
                employeeService.create(employee));
        assertEquals("Employee must be 18~65 years old", employeeCreateException.getMessage());

    }


}
