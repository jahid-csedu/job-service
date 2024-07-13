package com.example.job_service.external;

import lombok.Data;

@Data
public class CompanyDto {
    private Long id;
    private String name;
    private String location;
    private Integer numberOfEmployees;
}
