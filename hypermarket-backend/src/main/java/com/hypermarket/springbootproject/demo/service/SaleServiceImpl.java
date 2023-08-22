package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Sale;
import com.hypermarket.springbootproject.demo.repository.SaleRepository;
import com.hypermarket.springbootproject.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getSpecificSale(int saleId) {
        return (Sale) getAllSales()
                .stream()
                .filter(s -> s.getSaleId() == saleId);
    }

    @Override
    public Sale saveSale(Sale sale) {
        Sale save = saleRepository.save(sale);
        return save;
    }
}
