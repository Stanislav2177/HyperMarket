package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> getAllSales();
    Sale getSpecificSale(int saleId);

    Sale saveSale(Sale sale);
}
