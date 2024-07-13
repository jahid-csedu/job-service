package com.example.job_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JobDto {
    private Long id;
    private String title;
    private String description;
    private Long companyId;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String location;
}
