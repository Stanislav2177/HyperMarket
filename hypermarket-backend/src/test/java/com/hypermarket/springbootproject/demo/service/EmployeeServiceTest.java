package com.hypermarket.springbootproject.demo.service;


import com.hypermarket.springbootproject.demo.entity.Employee;
import com.hypermarket.springbootproject.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeServiceImpl service;

    @Mock
    EmployeeRepository dao;

    @Test
    void testFindAllEmployees(){
        List<Employee> list = new ArrayList<Employee>();

        Employee empOne = new Employee(1,
                "Stan", "Seller",
                1, 1, null);

        Employee empTwo = new Employee(1,
                "Stanislav", "Seller",
                1, 1, null);

        Employee empThree = new Employee(1,
                "Stani", "Seller",
                1, 1, null);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(dao.findAll()).thenReturn(list);

        List<Employee> employeeList = service.getAllEmployees();

        assertEquals(3, employeeList.size());
        verify(dao, times(1)).findAll();

    }
    @Test
    void testSaveEmployeeWithOnlyNameAndPosition() {
        Employee employee = service.saveEmployee("Stan", "Seller", null);

        Employee savedEmployee = new Employee(1, "Stan", "Seller", 1, 1, null);
        when(dao.save(employee)).thenReturn(savedEmployee);

        Employee createdEmployee = service.saveEmployee("Stan", "Seller", null);


        assertEquals(1, createdEmployee.getDepartmentId());
        assertEquals(1, createdEmployee.getManagerId());
    }

    @Test
    void testUpdateEmployee(){
        Employee employeeToUpdate = service.saveEmployee("Stan", "Seller", null);
        employeeToUpdate.setEmployeeId(1);


        Employee updatedEmployee = new Employee( "Stan",
                "Heaver", 3, 3, null);
        updatedEmployee.setEmployeeId(1);

        when(dao.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = service.updateEmployee(updatedEmployee, employeeToUpdate.getEmployeeId());


        assertEquals(updatedEmployee.getEmployeeId(), result.getEmployeeId());
        assertEquals(updatedEmployee.getEmployeeName(), result.getEmployeeName());
        assertEquals(updatedEmployee.getPosition(), result.getPosition());
        assertEquals(updatedEmployee.getDepartmentId(), result.getDepartmentId());
        assertEquals(updatedEmployee.getManagerId(), result.getManagerId());

        verify(dao, times(1)).save(argThat(arg -> arg.getEmployeeId() == employeeToUpdate.getEmployeeId()
                && arg.getEmployeeName().equals("Stan")
                && arg.getPosition().equals("Heaver")));

    }

    @Test
    public void testUpdateEmployeeSuccess() {
        // Create a sample employee and a mock result
        Employee existingEmployee = service.saveEmployee("Stan", "Seller", null);
        Employee updatedEmployee = new Employee( "Stan NEW",
                "Heaver", 3, 3, null);

        when(dao.findById(anyInt())).thenReturn(Optional.of(existingEmployee));
        when(dao.save(existingEmployee)).thenReturn(updatedEmployee);


        // Call the updateEmployee method
        Employee result = service.updateEmployee(updatedEmployee, 1);

        System.out.println(result);
        // Verify that the repository methods were called with the correct arguments
        verify(dao, times(1)).findById(1);
        verify(dao, times(2)).save(existingEmployee);

        // Assert that the result matches the updatedEmployee
        assertEquals(updatedEmployee, result);
    }



}
