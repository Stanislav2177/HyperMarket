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
    public void testUpdateEmployeeSuccess() {
        Employee existingEmployee = service.saveEmployee("Stan", "Seller", null);
        Employee updatedEmployee = new Employee( "Stan NEW",
                "Heaver", 3, 3, null);

        when(dao.findById(anyInt())).thenReturn(Optional.of(existingEmployee));
        when(dao.save(existingEmployee)).thenReturn(updatedEmployee);


        Employee result = service.updateEmployee(updatedEmployee, 1);

        System.out.println(result);

        verify(dao, times(1)).findById(1);
        verify(dao, times(2)).save(existingEmployee);

        assertEquals(updatedEmployee, result);
    }



}
