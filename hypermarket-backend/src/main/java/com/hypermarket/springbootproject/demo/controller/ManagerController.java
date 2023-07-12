package com.hypermarket.springbootproject.demo.controller;

import com.hypermarket.springbootproject.demo.entity.Manager;
import com.hypermarket.springbootproject.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping
    public ResponseEntity<Manager> createManager(@RequestBody Manager manager){
        Manager createManager = managerService.createManager(manager);
        return ResponseEntity.status(HttpStatus.CREATED).body(createManager);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable int id) throws ChangeSetPersister.NotFoundException {
        Manager manager = managerService.getManagerById(id);
        return ResponseEntity.ok(manager);
    }

    @GetMapping
    public ResponseEntity<List<Manager>> getAllManagers(){
        List<Manager> allManagers = managerService.getAllManagers();
        return ResponseEntity.ok(allManagers);
    }

    @PutMapping("/{managerId}")
    public ResponseEntity<Manager> updateManager(
            @PathVariable int managerId,
            @RequestBody Manager updatedManager) {
        try {
            Manager updated = managerService.updateManager(managerId, updatedManager);
            return ResponseEntity.ok(updated);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{managerId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int managerId) {
        managerService.deleteManager(managerId);
        return ResponseEntity.noContent().build();
    }

}



