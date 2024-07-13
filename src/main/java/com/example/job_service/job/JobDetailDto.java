package com.example.job_service.job;

import com.example.job_service.external.CompanyDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class JobDetailDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String location;
    private CompanyDto company;
}
