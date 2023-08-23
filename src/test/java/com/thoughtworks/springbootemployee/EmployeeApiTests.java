package com.thoughtworks.springbootemployee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest

@AutoConfigureMockMvc
class EmployeeApiTests {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MockMvc mockMvcClient;
    @BeforeEach
    void cleanUpEmployeeData() {
        employeeRepository.cleanAll();
    }

    @Test
    void should_return_all_given_all_employees_when_perform_get_employees() throws Exception {

        //given
        Employee alice = employeeRepository.addEmployee(new Employee(1L,"Alice", 24, "Female", 9000, 1L));

        //when, then

        mockMvcClient.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(alice.getId()))
                .andExpect(jsonPath("$[0].name").value(alice.getName()))
                .andExpect(jsonPath("$[0].age").value(alice.getAge()))
                .andExpect(jsonPath("$[0].gender").value(alice.getGender()))
                .andExpect(jsonPath("$[0].salary").value(alice.getSalary()))
                .andExpect(jsonPath("$[0].companyId").value(alice.getCompanyId()));

    }
    @Test
    void should_return_the_employee_when_get_employee_given_employee_Id() throws Exception {
    //given
        Employee alice = employeeRepository.addEmployee(new Employee(1L,"Alice", 24, "Female", 9000, 1L));
        Employee bob = employeeRepository.addEmployee(new Employee(2L,"Bob", 24, "Male", 9000, 2L));

     //when,then
        mockMvcClient.perform(MockMvcRequestBuilders.get("/employees/"+alice.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(alice.getName()))
                .andExpect(jsonPath("$.id").value(alice.getId()))
                .andExpect(jsonPath("$.age").value(alice.getAge()))
                .andExpect(jsonPath("$.gender").value(alice.getGender()))
                .andExpect(jsonPath("$.salary").value(alice.getSalary()))
                .andExpect(jsonPath("$.companyId").value(alice.getCompanyId()));
    }
    @Test
    void should_return_404_not_found_when_perform_get_employee_given_do_not_exist() throws Exception {
    //given
     long doesNotExistEmployeeId = 99L;
     //when
     mockMvcClient.perform(MockMvcRequestBuilders.get("/employees/"+ doesNotExistEmployeeId))
             .andExpect(status().isNotFound());
    }
    @Test
    void should_return_employees_by_gender_when_perform_get_employee_given_gender() throws Exception {
    //given
        Employee alice = employeeRepository.addEmployee(new Employee(1L,"Alice", 24, "Female", 9000, 1L));
        Employee bob = employeeRepository.addEmployee(new Employee(2L,"Bob", 24, "Male", 9000, 2L));
     //when
        mockMvcClient.perform(MockMvcRequestBuilders.get("/employees/").param("gender", "Female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(alice.getId()))
                .andExpect(jsonPath("$[0].name").value(alice.getName()))
                .andExpect(jsonPath("$[0].age").value(alice.getAge()))
                .andExpect(jsonPath("$[0].gender").value(alice.getGender()))
                .andExpect(jsonPath("$[0].salary").value(alice.getSalary()))
                .andExpect(jsonPath("$[0].companyId").value(alice.getCompanyId()));
    }
    @Test
    void should_return_the_employee_when_perform_post_employees_given_a_new_employee_with_JSON_format() throws Exception {
    //given
        Employee newEmployee = new Employee(1L,"Alice", 24, "Female", 9000, 1L);

        //when
        mockMvcClient.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(newEmployee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(notNullValue()))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.age").value("24"))
                .andExpect(jsonPath("$.gender").value("Female"))
                .andExpect(jsonPath("$.salary").value(9000))
                .andExpect(jsonPath("$.companyId").value(1L));

    }

    @Test
    void should_return_updated_employee_when_perform_put_given_employee_age_and() {
    //given

     //when

     //then
    }
}
