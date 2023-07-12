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
@Table(name = "managers")
public class Manager {

    @Id
    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "contact_info")
    private String contact;
}
