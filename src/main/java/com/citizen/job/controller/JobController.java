package com.citizen.job.controller;

import com.citizen.job.client.UserInterface;
import com.citizen.job.dto.*;
import com.citizen.job.entity.Job;
import com.citizen.job.response.ApiResponse;
import com.citizen.job.service.JobService;
import com.citizen.job.utils.UserUnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/work")
public class JobController {

    private final JobService jobService;
    private final UserInterface userInterface;

    public JobController(@Autowired JobService jobService, @Autowired UserInterface userInterface) {
        this.jobService = jobService;
        this.userInterface = userInterface;
    }

    @PostMapping("/jobs")
    public ResponseEntity<ApiResponse<Job>> addJob(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @RequestBody Job job) {

        validateAdmin(adminId, email, token);

        Job saveJob = jobService.saveJob(job);
        ApiResponse<Job> response = ApiResponse.success(saveJob, "Job added");
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/jobs")
    public ResponseEntity<ApiResponse<List<Job>>> getAllJobs(
            @RequestHeader("id") Long citizenId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token){

        EmailResponseDto _emailResponseDto = validateUser(email, citizenId, token);

        List<Job> _jobs = hasRole(_emailResponseDto, Role.ADMIN) ? jobService.findAllJobs() : jobService.findAllActiveJobs();
        ApiResponse<List<Job>> response = ApiResponse.success(_jobs, "List of All Jobs");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<ApiResponse<Job>> findJobById(
            @RequestHeader("id") Long citizenId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id) {

        EmailResponseDto _emailResponseDto = validateUser(email, citizenId, token);

        Job _job = hasRole(_emailResponseDto, Role.ADMIN) ? jobService.findJobById(id) : jobService.findActiveJobById(id);
        ApiResponse<Job> response = ApiResponse.success(_job, "Job by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<ApiResponse<Job>> updateJob(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id,
            @RequestBody Job newJob) {

        validateAdmin(adminId, email, token);

        Job updatedJob = jobService.updateJobById(id, newJob);
        ApiResponse<Job> response = ApiResponse.success(updatedJob, "Job updated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<ApiResponse<Job>> deleteJob(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id) {

        validateAdmin(adminId, email, token);

        Job _job = jobService.deleteJobById(id);
        ApiResponse<Job> response = ApiResponse.success(_job, "Job deleted successfully with ID: " + id);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/jobs/delete/{id}")
    public ResponseEntity<ApiResponse<Job>> deleteJobPermanently(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id){

        validateAdmin(adminId, email, token);

        Job _job = jobService.deleteJobPremanentlyById(id);
        ApiResponse<Job> response = ApiResponse.success(_job, "Job deleted permanently successfully with ID: " + id);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/jobs/active")
    public ResponseEntity<ApiResponse<List<Job>>> getActiveJobs(
            @RequestHeader("id") Long citizenId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token) {

        validateUser(email, citizenId, token);

        List<Job> _jobs = jobService.findAllActiveJobs();
        ApiResponse<List<Job>> response = ApiResponse.success(_jobs, "List of Active Jobs");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/jobs/active/count")
    public ResponseEntity<ApiResponse<OpportunitiesCountDto>> getActiveJobsCount(){
        OpportunitiesCountDto opportunitiesCount = jobService.getOpportunitiesCount();
        ApiResponse<OpportunitiesCountDto> response = ApiResponse.success(opportunitiesCount, "Opportunities count");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/jobs/inactive")
    public ResponseEntity<ApiResponse<List<Job>>> getInactiveJobs(
            @RequestHeader("id") Long citizenId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token) {

        validateAdmin(citizenId, email, token);

        List<Job> _jobs = jobService.findAllInactiveJobs();
        ApiResponse<List<Job>> response = ApiResponse.success(_jobs, "List of Inactive Jobs");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/jobs/{id}/activate")
    public ResponseEntity<ApiResponse<Job>> activateJob(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id) {

        validateAdmin(adminId, email, token);

        Job _job = jobService.activateJobById(id);
        ApiResponse<Job> response = ApiResponse.success(_job, "Job activated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/jobs/{id}/deactivate")
    public ResponseEntity<ApiResponse<Job>> deactivateJob(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id) {

        validateAdmin(adminId, email, token);

        Job _job = jobService.deactivateJobById(id);
        ApiResponse<Job> response = ApiResponse.success(_job, "Job deactivated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/jobs/purge-expired")
    public ResponseEntity<ApiResponse<Job>> purgeExpiredJobs(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token
            ) {

        validateAdmin(adminId, email, token);

        Job _job = jobService.purgeExpiredJobs();
        ApiResponse<Job> response = ApiResponse.success(_job, "Job deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void validateAdmin(@RequestHeader("id") Long adminId, @RequestHeader("email") String email, @RequestHeader("token") String token) {
        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto _emailResponseDto = Objects.requireNonNull(userInterface.getProfileByEmail(email).getBody()).getData();

        if (!adminId.equals(_emailResponseDto.getId()) || !_emailResponseDto.getEmail().equals(email) || !hasRole(_emailResponseDto, Role.ADMIN)) {
            throw new UserUnauthorizedException("User is not authorized");
        }
    }

    private EmailResponseDto  validateUser(@RequestHeader("email") String email, @RequestHeader("id") Long citizenId, @RequestHeader("token") String token) {
        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto _emailResponseDto = Objects.requireNonNull(userInterface.getProfileByEmail(email).getBody()).getData();

        if (!citizenId.equals(_emailResponseDto.getId()) || !_emailResponseDto.getEmail().equals(email) || !(hasRole(_emailResponseDto, Role.CITIZEN) || hasRole(_emailResponseDto, Role.ADMIN))) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        return _emailResponseDto;
    }

    private boolean hasRole(EmailResponseDto dto, Role targetRole) {
        if (dto == null || dto.getRole() == null || dto.getRole().isEmpty()) {
            return true;
        }
        return dto.getRole().stream()
                .anyMatch(role -> role != null && role.getName() == targetRole);
    }


}

