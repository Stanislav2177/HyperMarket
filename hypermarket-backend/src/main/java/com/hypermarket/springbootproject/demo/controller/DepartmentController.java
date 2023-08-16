package com.hypermarket.springbootproject.demo.controller;
import com.hypermarket.springbootproject.demo.entity.Department;
import com.hypermarket.springbootproject.demo.entity.Manager;
import com.hypermarket.springbootproject.demo.service.DepartmentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    public final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int departmentId){
        Department departmentById = departmentService.getDepartmentById(departmentId);

        return ResponseEntity.ok(departmentById);
    }

    @PostMapping("/post")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
        Department department1 = departmentService.createDepartment(department);

        return ResponseEntity.ok(department1);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping("/update/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department, @PathVariable int departmentId){
        return ResponseEntity
                .ok(departmentService.updateDepartment(department, departmentId));
    }

    @DeleteMapping("/delete/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manager/{departmentId}")
    public ResponseEntity<Manager> getDepartmentManager(@PathVariable int departmentId){
        return ResponseEntity
                .ok(departmentService.getDepartmentManager(departmentId));
    }

}
