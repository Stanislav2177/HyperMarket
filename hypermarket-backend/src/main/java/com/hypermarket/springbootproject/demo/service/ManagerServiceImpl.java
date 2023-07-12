package com.hypermarket.springbootproject.demo.service;


import com.hypermarket.springbootproject.demo.entity.Manager;
import com.hypermarket.springbootproject.demo.repository.ManagerRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager getManagerById(int managerId) throws ChangeSetPersister.NotFoundException {
        return managerRepository.findById(managerId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public List<Manager> getManagersByDepartmentId(int departmentId) {

        //TODO: implement Department table, and get the manager From DepartmentRepository

//        return managerRepository.findByDepartmentId(departmentId);

        return null;

    }

    @Override
    public Manager updateManager(int managerId, Manager updatedManager) throws ChangeSetPersister.NotFoundException {
        Manager existingManager = getManagerById(managerId);
        existingManager.setManagerName(updatedManager.getManagerName());
        existingManager.setContact(updatedManager.getContact());
        existingManager.setDepartmentId(updatedManager.getDepartmentId());
        return managerRepository.save(existingManager);
    }

    @Override
    public void deleteManager(int managerId) {
        managerRepository.deleteById(managerId);
    }
}