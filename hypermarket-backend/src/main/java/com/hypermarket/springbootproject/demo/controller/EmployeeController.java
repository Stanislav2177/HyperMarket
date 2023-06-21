package com.hypermarket.springbootproject.demo.controller;

import com.hypermarket.springbootproject.demo.entity.Employee;
import com.hypermarket.springbootproject.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getSpecificEmployee(@PathVariable int id){
        System.out.println(employeeService.getSpecificEmployee(id));
        return employeeService.getAllEmployees().get(id);
    }
    @PostMapping("/post")
    public Employee createEmployee(@RequestBody Employee employee) {
        String name = employee.getEmployeeName();
        String position = employee.getPosition();
        String contact = employee.getContact();

        System.out.println(name);
        System.out.println(position);
        System.out.println(contact);

        return employeeService.saveEmployee(name, position, contact);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        System.out.println(id);
        employeeService.deleteEmployee(id);
    }




}
