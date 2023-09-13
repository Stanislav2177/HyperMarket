package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.exception.SaleNoteInfoException;
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
        try {
            List<CombinedDto> saleNotes = repository.getProductSaleEmployeeManufacturerInfo();

            if(!saleNotes.isEmpty()){
                return saleNotes;
            }else {
                throw new SaleNoteInfoException("There is no sale notes");
            }
        }catch (Exception e){
            throw new SaleNoteInfoException("Problem getting the sales note");
        }

    }

    public List<CombinedDto> getProductSaleEmployeeManufacturerInfo(int productId) {
        try {
            return repository.getProductSaleEmployeeManufacturerInfo(productId);
        }catch (Exception e){
            throw new SaleNoteInfoException("Problem getting the sale note" + productId);
        }
    }
}
