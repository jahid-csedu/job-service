package com.example.job_service.job;

import com.example.job_service.dto.JobDetailDto;
import com.example.job_service.dto.JobDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @PostMapping
    public ResponseEntity<Long> createJob(@RequestBody JobDto jobDto) {
        return ResponseEntity.ok(jobService.create(jobDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable Long id, @RequestBody JobDto jobDto) {
        return ResponseEntity.ok(jobService.update(id, jobDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJob(@PathVariable Long id) {
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDetailDto> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobDetailDto>> getAllJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }
}
