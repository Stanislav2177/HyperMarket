package com.hypermarket.springbootproject.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CombinedDto {
    private int productId;

    private String productName;

    private double price;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date expiryDate;

    private int productQuantity;

    private int saleId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date saleDate;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC")
    private Time saleTime;

    private int saleQuantity;

    private int employeeId;

    private String employeeName;

    private String employeePosition;

    private int manufacturerId;

    private String manufacturerName;

    private String country;

    private String contactInfo;
}
