package com.hypermarket.springbootproject.demo.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hypermarket.springbootproject.demo.dto.CombinedDto;
import com.hypermarket.springbootproject.demo.repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SaleServiceTest {
    @InjectMocks
    SaleServiceImpl saleService;

    @Mock
    SaleRepository dao;

    @Test
    public void testJsonSerializationDeserialization() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        CombinedDto originalDto = new CombinedDto();
        originalDto.setProductId(1);
        originalDto.setProductName("Test Product");
        originalDto.setContactInfo("something");

        System.out.println(originalDto.getContactInfo());
        // Set other properties...

        // Serialize to JSON
        String json = objectMapper.writeValueAsString(originalDto);

        // Deserialize back to CombinedDto
        CombinedDto deserializedDto = objectMapper.readValue(json, CombinedDto.class);

        // Compare the original and deserialized objects
        assertEquals(originalDto, deserializedDto);
    }

}
