package com.citizen.job.controller;

import com.citizen.job.entity.Job;
import com.citizen.job.response.ApiResponse;
import com.citizen.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/work")
public class JobController {

    private final JobService jobService;

    public JobController(@Autowired JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/jobs")
    public ResponseEntity<ApiResponse<Job>> addJob(@RequestBody Job job) {
        Job saveJob = jobService.saveJob(job);
        ApiResponse<Job> response = ApiResponse.success(saveJob, "Job added");
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/jobs")
    public ResponseEntity<ApiResponse<List<Job>>> getAllJobs(){
        List<Job> _jobs = jobService.findAllJobs();
        ApiResponse<List<Job>> response = ApiResponse.success(_jobs, "List of All Jobs");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<ApiResponse<Job>> findJobById(@PathVariable Long id) {
        Job updatedJob = jobService.findJobById(id);
        ApiResponse<Job> response = ApiResponse.success(updatedJob, "Job by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<ApiResponse<Job>> updateJob(@PathVariable Long id, @RequestBody Job newJob) {
        Job updatedJob = jobService.updateJobById(id, newJob);
        ApiResponse<Job> response = ApiResponse.success(updatedJob, "Job updated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<ApiResponse<Job>> deleteJob(@PathVariable Long id) {
        Job _job = jobService.deleteJobById(id);
        ApiResponse<Job> response = ApiResponse.success(_job, "Job deleted successfully with ID: " + id);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/jobs/active")
    public ResponseEntity<ApiResponse<List<Job>>> getActiveJobs() {
        List<Job> _jobs = jobService.findAllActiveJobs();
        ApiResponse<List<Job>> response = ApiResponse.success(_jobs, "List of Active Jobs");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/jobs/inactive")
    public ResponseEntity<ApiResponse<List<Job>>> getInactiveJobs() {
        List<Job> _jobs = jobService.findAllInactiveJobs();
        ApiResponse<List<Job>> response = ApiResponse.success(_jobs, "List of Inactive Jobs");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/jobs/{id}/activate")
    public ResponseEntity<ApiResponse<Job>> activateJob(@PathVariable Long id) {
        Job _job = jobService.activateJobById(id);
        ApiResponse<Job> response = ApiResponse.success(_job, "Job activated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/jobs/{id}/deactivate")
    public ResponseEntity<ApiResponse<Job>> deactivateJob(@PathVariable Long id) {
        Job _job = jobService.deactivateJobById(id);
        ApiResponse<Job> response = ApiResponse.success(_job, "Job deactivated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/jobs/purge-expired")
    public ResponseEntity<ApiResponse<Job>> purgeExpiredJobs() {
        Job _job = jobService.purgeExpiredJobs();
        ApiResponse<Job> response = ApiResponse.success(_job, "Job deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

