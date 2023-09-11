package com.hypermarket.springbootproject.demo.service;


import com.hypermarket.springbootproject.demo.repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaleServiceTest {
    @InjectMocks
    SaleServiceImpl saleService;

    @Mock
    SaleRepository dao;



}
