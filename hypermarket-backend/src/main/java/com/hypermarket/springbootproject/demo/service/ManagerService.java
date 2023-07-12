package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Manager;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ManagerService {
    Manager createManager(Manager manager);

    Manager getManagerById(int managerId) throws ChangeSetPersister.NotFoundException;

    List<Manager> getAllManagers();

    List<Manager> getManagersByDepartmentId(int departmentId);

    Manager updateManager(int managerId, Manager updatedManager) throws ChangeSetPersister.NotFoundException;

    void deleteManager(int managerId);
}
