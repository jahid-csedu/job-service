package com.example.job_service.job;

import com.example.job_service.external.CompanyClient;
import com.example.job_service.external.CompanyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final CompanyClient companyClient;

    public Long create(JobDto jobDto) {
        log.debug("Job to be created: {}", jobDto);
        Job savedJob = jobRepository.save(jobMapper.dtoToEntity(jobDto));
        return savedJob.getId();
    }

    public JobDto update(Long id, JobDto jobDto) {
        log.info("Job to be updated: {}", jobDto);
        Job oldJob = jobRepository.findById(id).orElse(null);
        if(oldJob != null) {
            jobMapper.toUpdatedEntity(oldJob, jobDto);
            Job newJob = jobRepository.save(oldJob);

            return jobMapper.entityToDto(newJob);
        }

        return null;
    }

    public void delete(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if(job != null) {
            jobRepository.deleteById(id);
        }
    }

    public JobDetailDto findById(Long id) {
        log.debug("Getting job by ID: {}", id);
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isEmpty()) {
            return null;
        }
        Job job = jobOptional.get();

        if(job.getCompanyId() != null) {
            return jobMapper.entityToDetailDto(job, companyClient.getCompanyById(job.getCompanyId()));
        }
        return jobMapper.entityToDetailDto(job, null);
    }

    public List<JobDetailDto> findAll() {
        List<Job> jobs = jobRepository.findAll();

        return getJobDetails(jobs);
    }

    private List<JobDetailDto> getJobDetails(List<Job> jobs) {
        Map<Long, CompanyDto> companies = companyClient.getCompanies();
        return jobs.stream()
                .map(job -> jobMapper.entityToDetailDto(job, companies.get(job.getCompanyId())))
                .toList();
    }
}
