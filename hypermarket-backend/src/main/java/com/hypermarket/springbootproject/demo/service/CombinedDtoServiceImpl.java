package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.repository.CombinedDtoRepository;
import com.hypermarket.springbootproject.demo.dto.CombinedDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombinedDtoServiceImpl implements CombinedDtoService {
    private final CombinedDtoRepository repository;

    public CombinedDtoServiceImpl(CombinedDtoRepository repository) {
        this.repository = repository;
    }

    public List<CombinedDto> getProductSaleEmployeeManufacturerInfo() {
        List<CombinedDto> productSaleEmployeeManufacturerInfo = repository.getProductSaleEmployeeManufacturerInfo();
        System.out.println(productSaleEmployeeManufacturerInfo.toString());

        return productSaleEmployeeManufacturerInfo;
    }

    public List<CombinedDto> getProductSaleEmployeeManufacturerInfo(int productId) {
        return repository.getProductSaleEmployeeManufacturerInfo(productId);
    }

}
