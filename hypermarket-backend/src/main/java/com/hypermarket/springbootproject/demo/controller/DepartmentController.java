package com.hypermarket.springbootproject.demo.controller;
import com.hypermarket.springbootproject.demo.entity.Department;
import com.hypermarket.springbootproject.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
