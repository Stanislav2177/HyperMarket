package com.hypermarket.springbootproject.demo.repository;

import com.hypermarket.springbootproject.demo.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
