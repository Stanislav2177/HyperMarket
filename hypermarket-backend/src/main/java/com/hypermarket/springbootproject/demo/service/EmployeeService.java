package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(String name, String position, String contact);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Employee employee, int id);
    void deleteEmployee(int id);
    Employee getSpecificEmployee(int id);

}
