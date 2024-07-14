package com.example.job_service.mapper;

import com.example.job_service.dto.JobDto;
import com.example.job_service.job.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobDto entityToDto(Job job);

    Job dtoToEntity(JobDto jobDto);

    List<JobDto> entityToDtoList(List<Job> jobs);

    @Mapping(target = "id", ignore = true)
    void toUpdatedEntity(@MappingTarget Job job, JobDto dto);
}
