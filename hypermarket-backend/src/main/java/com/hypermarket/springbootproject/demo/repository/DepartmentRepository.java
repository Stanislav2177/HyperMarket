package com.hypermarket.springbootproject.demo.repository;

import com.hypermarket.springbootproject.demo.entity.Department;
import com.hypermarket.springbootproject.demo.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
