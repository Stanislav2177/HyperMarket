package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Employee;
import com.hypermarket.springbootproject.demo.exception.EmployeeNotFoundException;
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
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> all = employeeRepository.findAll();

        return all;
    }

    @Override
    public Employee updateEmployee(Employee updatedEmp, int oldEmpId) {
        try{
            Employee existingEmployee = getSpecificEmployee(oldEmpId);

            existingEmployee.setEmployeeId(oldEmpId);
            existingEmployee.setEmployeeName(updatedEmp.getEmployeeName());
            existingEmployee.setContact(updatedEmp.getContact());


            String updatedPosition = updatedEmp.getPosition();
            int fixedDepId = existingEmployee.getDepartmentId();
            int fixedManId = existingEmployee.getManagerId();

            if(!existingEmployee.getPosition().equals(updatedPosition)){
                Result result = getResult(updatedPosition, fixedDepId, fixedManId);
                fixedManId = result.managerId;
                fixedDepId = result.departmentId;
            }

            existingEmployee.setPosition(updatedPosition);
            existingEmployee.setDepartmentId(fixedDepId);
            existingEmployee.setManagerId(fixedManId);

            employeeRepository.save(existingEmployee);

            return existingEmployee;
        }catch (EmployeeNotFoundException ex){
            throw new EmployeeNotFoundException("Failed to update updatedEmp : " + ex.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int id) {
        Employee specificEmployee = getSpecificEmployee(id);
        employeeRepository.delete(specificEmployee);
    }

    @Override
    public Employee getSpecificEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));


        return employee;
    }

    private Employee createEmployee(String name, String position, String contact) {
        int departmentId = 0;
        int managerId = 0;

        Result result = getResult(position, departmentId, managerId);
        Employee employee = new Employee(0,name, position, result.departmentId(), result.managerId(), contact);

        return employee;
    }

    private static Result getResult(String position, int departmentId, int managerId) {
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
        Result result = new Result(departmentId, managerId);
        return result;
    }

    private record Result(int departmentId, int managerId) {
    }
}
