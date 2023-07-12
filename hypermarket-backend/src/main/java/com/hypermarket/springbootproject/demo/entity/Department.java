package com.hypermarket.springbootproject.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "manager_id")
    private int managerId;

}
