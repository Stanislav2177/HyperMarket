package com.hypermarket.springbootproject.demo.repository;

import com.hypermarket.springbootproject.demo.dto.CombinedDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CombinedDtoRepository {
    private final JdbcTemplate jdbcTemplate;

    public CombinedDtoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CombinedDto> getProductSaleEmployeeManufacturerInfo() {
        String sql = "CALL GetSalesInfo()";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CombinedDto.class));
    }

    public List<CombinedDto> getProductSaleEmployeeManufacturerInfo(int productId) {
        String procedureCall = "CALL GetSalesInfoFiltered(?)";
        return jdbcTemplate.query(procedureCall, new Object[]{productId}, new BeanPropertyRowMapper<>(CombinedDto.class));
    }
}
