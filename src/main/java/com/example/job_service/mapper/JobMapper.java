package com.example.job_service.mapper;

import com.example.job_service.external.CompanyDto;
import com.example.job_service.job.Job;
import com.example.job_service.dto.JobDetailDto;
import com.example.job_service.dto.JobDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobDto entityToDto(Job job);

    @Mapping(target = "id", source = "job.id")
    @Mapping(target = "location", source = "job.location")
    @Mapping(target = "company", source = "company")
    JobDetailDto entityToDetailDto(Job job, CompanyDto company);

    Job dtoToEntity(JobDto jobDto);

    List<JobDto> entityToDtoList(List<Job> jobs);

    @Mapping(target = "id", ignore = true)
    void toUpdatedEntity(@MappingTarget Job job, JobDto dto);
}
