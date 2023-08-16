package com.hypermarket.springbootproject.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
