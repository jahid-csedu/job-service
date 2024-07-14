package com.example.job_service.job;

import com.example.job_service.dto.JobDto;
import com.example.job_service.mapper.JobMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public Long create(JobDto jobDto) {
        log.debug("Job to be created: {}", jobDto);
        Job savedJob = jobRepository.save(jobMapper.dtoToEntity(jobDto));
        return savedJob.getId();
    }

    public JobDto update(Long id, JobDto jobDto) {
        log.info("Job to be updated: {}", jobDto);
        Job oldJob = jobRepository.findById(id).orElse(null);
        if (oldJob != null) {
            jobMapper.toUpdatedEntity(oldJob, jobDto);
            Job newJob = jobRepository.save(oldJob);

            return jobMapper.entityToDto(newJob);
        }

        return null;
    }

    public void delete(Long id) {
        jobRepository.findById(id).ifPresent(job -> jobRepository.deleteById(id));
    }

    public JobDto findById(Long id) {
        log.debug("Getting job by ID: {}", id);
        Optional<Job> jobOptional = jobRepository.findById(id);
        return jobOptional.map(jobMapper::entityToDto).orElse(null);
    }

    public List<JobDto> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobMapper.entityToDtoList(jobs);
    }
}
