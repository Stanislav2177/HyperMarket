package com.hypermarket.springbootproject.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Employee(String employeeName, String position, int departmentId, int managerId, String contact) {
        this.employeeName = employeeName;
        this.position = position;
        this.departmentId = departmentId;
        this.managerId = managerId;
        this.contact = contact;
    }
}
