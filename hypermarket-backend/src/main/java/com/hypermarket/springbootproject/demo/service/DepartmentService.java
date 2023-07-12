package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Department;
import com.hypermarket.springbootproject.demo.entity.Manager;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);

    Department getDepartmentById(int departmentId);

    List<Department> getAllDepartments();

    Manager getDepartmentManager(int departmentId);


    Department updateDepartment(int departmentId, Department updatedDepartment);

    void deleteDepartment(int departmentId);
}
