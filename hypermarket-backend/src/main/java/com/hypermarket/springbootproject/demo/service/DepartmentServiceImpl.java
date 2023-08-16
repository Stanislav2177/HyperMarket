package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Department;
import com.hypermarket.springbootproject.demo.entity.Manager;
import com.hypermarket.springbootproject.demo.repository.DepartmentRepository;
import com.hypermarket.springbootproject.demo.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ManagerRepository managerRepository) {
        this.departmentRepository = departmentRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        try {
            return departmentRepository.findById(departmentId)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Manager getDepartmentManager(int departmentId) {
        return (Manager) managerRepository.findAll()
                .stream()
                .filter(m -> m.getDepartmentId() == departmentId);
    }


    @Override
    public Department updateDepartment(Department updatedDepartment, int departmentId) {
        Department existingDepartment = null;
        try {
            existingDepartment = departmentRepository.findById(departmentId)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

        existingDepartment.setDepartmentName(updatedDepartment.getDepartmentName());
        existingDepartment.setManagerId(updatedDepartment.getManagerId());

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(int departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
