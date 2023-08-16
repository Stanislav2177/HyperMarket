package com.hypermarket.springbootproject.demo.repository;

import com.hypermarket.springbootproject.demo.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
}
