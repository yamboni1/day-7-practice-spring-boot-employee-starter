package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeCreateException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {
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
        Employee alice = new Employee(1L,"Alice", 24, "Female", 9000, 1L,true);
        Employee savedEmployee = new Employee(1L,"Alice", 24, "Female", 9000, 1L,true);
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
     Employee employee = new Employee(1L,"Alice", 17, "Female", 9000, 1L ,true);
     //when
     EmployeeCreateException employeeCreateException = assertThrows(EmployeeCreateException.class, ()->
             employeeService.create(employee));
     assertEquals("Employee must be 18~65 years old", employeeCreateException.getMessage());

    }
    @Test
    void should_throw_exception_when_create_given_employee_service_and_employee_age_greater_than_65() {
    //given
        Employee employee = new Employee(1L,"Alice", 66, "Female", 9000, 1L, true);

        //when
        EmployeeCreateException employeeCreateException = assertThrows(EmployeeCreateException.class, ()->
                employeeService.create(employee));
        assertEquals("Employee must be 18~65 years old", employeeCreateException.getMessage());

    }
    @Test
    void should_return_active_employee_when_create_given_employee_service_and_employee_with_valid_age() {
    //given
        Employee employee = new Employee(1L,"Alice", 20, "Female", 9000, 1L,true);
        Employee savedEmployee = new Employee(1L,"Alice", 20, "Female", 9000, 1L, true);
        when(mockedEmployeeRepository.addEmployee(employee)).thenReturn(savedEmployee);
        //when
        Employee employeeResponse = employeeService.create(employee);
     
     //then
        assertEquals(savedEmployee.getId(),employeeResponse.getId());
        assertEquals("Alice",employeeResponse.getName());
        assertEquals(20,employeeResponse.getAge());
        assertEquals("Female",employeeResponse.getGender());
        assertEquals(9000,employeeResponse.getSalary());
        assertEquals(1L,employeeResponse.getCompanyId());
        assertTrue(employeeResponse.isActive());
    }



//    @Test
//    void should_set_active_false_employee_when_delete_given_employee_service_and_employee_id() {
//    //given
//        Employee employee = new Employee(1L,"Alice", 66, "Female", 9000, 1L);
//        employee.setActive(Boolean.TRUE);
//        when(mockedEmployeeRepository.findByEmployeeId(employee.getId())).thenReturn(employee);
//        //when
//        employeeService.delete(employee.getId());
//     //then
//        verify(mockedEmployeeRepository.update()
//    }


}
