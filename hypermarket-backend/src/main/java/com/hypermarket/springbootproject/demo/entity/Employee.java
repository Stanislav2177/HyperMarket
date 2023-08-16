package com.hypermarket.springbootproject.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "position")
    private String position;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "contact_info")
    private String contact;


}
