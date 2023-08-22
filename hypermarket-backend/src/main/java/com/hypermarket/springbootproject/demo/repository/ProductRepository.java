package com.hypermarket.springbootproject.demo.repository;

import com.hypermarket.springbootproject.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
