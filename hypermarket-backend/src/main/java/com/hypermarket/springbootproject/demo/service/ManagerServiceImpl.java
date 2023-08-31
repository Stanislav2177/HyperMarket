package com.hypermarket.springbootproject.demo.service;


import com.hypermarket.springbootproject.demo.entity.Department;
import com.hypermarket.springbootproject.demo.entity.Employee;
import com.hypermarket.springbootproject.demo.entity.Manager;
import com.hypermarket.springbootproject.demo.exception.ManagerNotFoundException;
import com.hypermarket.springbootproject.demo.repository.DepartmentRepository;
import com.hypermarket.springbootproject.demo.repository.EmployeeRepository;
import com.hypermarket.springbootproject.demo.repository.ManagerRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;


    public ManagerServiceImpl(ManagerRepository managerRepository, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.managerRepository = managerRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager getManagerById(int managerId) {
        return managerRepository.findById(managerId)
                .orElseThrow(() -> new ManagerNotFoundException("Manager not found with ID: " + managerId));
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public Department getManagerByDepartmentId(int managerId) {
        List<Department> all = departmentRepository.findAll();
        for (Department department : all) {
            if (department.getManagerId() == managerId) {
                return department;
            }
        }

        throw new ManagerNotFoundException("Manager not found for department with ID: " + managerId);
    }

    @Override
    public Manager updateManager(int managerId, Manager updatedManager) {
        try {
            Manager existingManager = getManagerById(managerId);
            existingManager.setManagerName(updatedManager.getManagerName());
            existingManager.setContact(updatedManager.getContact());
            existingManager.setDepartmentId(updatedManager.getDepartmentId());
            return managerRepository.save(existingManager);
        } catch (ManagerNotFoundException ex) {
            throw new RuntimeException("Failed to update manager: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteManager(int managerId) {
        try {
            managerRepository.deleteById(managerId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ManagerNotFoundException("Manager not found with ID: " + managerId);
        }
    }

    @Override
    public List<Employee> getAllEmployees(int managerId) {
        List<Employee> employeesFromSpecificManager = new ArrayList<>();
        List<Employee> all = employeeRepository.findAll();

        for (Employee employee : all) {
            if (employee.getManagerId() == managerId) {
                employeesFromSpecificManager.add(employee);
            }
        }

        if (employeesFromSpecificManager.isEmpty()) {
            throw new ManagerNotFoundException("No employees found for manager with ID: " + managerId);
        }

        return employeesFromSpecificManager;
    }
}