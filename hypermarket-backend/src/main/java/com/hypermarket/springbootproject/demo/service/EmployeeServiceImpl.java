package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Employee;
import com.hypermarket.springbootproject.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(String name, String position, String contact) {
        Employee employee = createEmployee(name, position, contact);
        employeeRepository.save(employee);
        System.out.println(employee.toString());
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee, int id) {
        return null;
    }

    @Override
    public void deleteEmployee(int id) {
        Employee specificEmployee = getSpecificEmployee(id);
        employeeRepository.delete(specificEmployee);
    }

    @Override
    public Employee getSpecificEmployee(int id) {
        Employee employee = getAllEmployees().get(id);
        return employee;
    }

    private Employee createEmployee(String name, String position, String contact) {
        int departmentId = 0;
        int managerId = 0;

        switch (position) {
            case "Seller" -> {
                departmentId = 1;
                managerId = 1;
            }
            case "Financial Advisor" -> {
                departmentId = 2;
                managerId = 2;
            }
            case "Loader", "Heaver" -> {
                departmentId = 3;
                managerId = 3;
            }
        }
        Employee employee = new Employee(0,name, position, departmentId, managerId, contact);

        return employee;
    }
}
