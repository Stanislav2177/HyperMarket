package com.hypermarket.springbootproject.demo.service;


import com.hypermarket.springbootproject.demo.entity.Employee;
import com.hypermarket.springbootproject.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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


        Employee updatedEmployee = new Employee( "Updated Stan",
                "Heaver", 3, 3, null);


        when(dao.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = service.updateEmployee(updatedEmployee, employeeToUpdate.getEmployeeId());
        verify(dao, times(1)).save(argThat(arg -> arg.getEmployeeId() == employeeToUpdate.getEmployeeId()
                && arg.getEmployeeName().equals("Stan")
                && arg.getPosition().equals("Heaver")));


        assertEquals(updatedEmployee.getEmployeeId(), result.getEmployeeId());
        assertEquals(updatedEmployee.getEmployeeName(), result.getEmployeeName());
        assertEquals(updatedEmployee.getPosition(), result.getPosition());
        assertEquals(updatedEmployee.getDepartmentId(), result.getDepartmentId());
        assertEquals(updatedEmployee.getManagerId(), result.getManagerId());

    }
}
