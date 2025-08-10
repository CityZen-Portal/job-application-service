package com.citizen.job.service.impl;

import com.citizen.job.dto.OpportunitiesCountDto;
import com.citizen.job.entity.Job;
import com.citizen.job.entity.Volunteer;
import com.citizen.job.repository.JobRepository;
import com.citizen.job.repository.VolunteerRepository;
import com.citizen.job.service.JobService;
import com.citizen.job.utils.JobNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;
    VolunteerRepository volunteerRepository;

    private JobServiceImpl(@Autowired JobRepository jobRepository, @Autowired VolunteerRepository volunteerRepository) {
        this.jobRepository = jobRepository;
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        Optional<Job> jobContainer = jobRepository.findById(id);
        if(jobContainer.isPresent()) {
            return jobContainer.get();
        }
        else throw new JobNotFoundException("Job not found");
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> findAllActiveJobs() {
        return jobRepository.findAllByIsActive(true);
    }

    @Override
    public OpportunitiesCountDto getOpportunitiesCount() {
        OpportunitiesCountDto opportunitiesCount = new OpportunitiesCountDto();
        List<Job> jobPost = jobRepository.findAllByIsActive(true);
        List<Volunteer> volunteerPost = volunteerRepository.findAllByIsActive(true);
        opportunitiesCount.setTotalActiveJobOpportunities(jobPost.size());
        opportunitiesCount.setTotalActiveVolunteerOpportunities(volunteerPost.size());
        opportunitiesCount.setTotalActiveOpportunities(volunteerPost.size() + jobPost.size());
        return opportunitiesCount;
    }

    @Override
    public List<Job> findAllInactiveJobs() {
        return jobRepository.findAllByIsActive(false);
    }

    @Override
    public Job updateJobById(Long id, Job job) {
        Optional<Job> jobContainer = jobRepository.findById(id);
        if(jobContainer.isPresent()) {
            Job _job = jobContainer.get();
            _job.setTitle(job.getTitle());
            _job.setDepartment(job.getDepartment());
            _job.setLocation(job.getLocation());
            _job.setDescription(job.getDescription());
            _job.setRequirements(job.getRequirements());
            _job.setContactPersonName(job.getContactPersonName());
            _job.setContactPhoneNumber(job.getContactPhoneNumber());
            _job.setContactEmail(job.getContactEmail());
            _job.setContactAddress(job.getContactAddress());
            _job.setIsActive(job.getIsActive());
            _job.setIsDeleted(job.getIsDeleted());
            _job.setDeadline(job.getDeadline());

            return jobRepository.save(_job);
        }
        else throw new JobNotFoundException("Job not found");
    }

    @Override
    public Job activateJobById(Long id) {
        Optional<Job> jobContainer = jobRepository.findById(id);
        if(jobContainer.isPresent()) {
            Job _job = jobContainer.get();
            _job.setIsActive(true);

            return jobRepository.save(_job);
        }
        else throw new JobNotFoundException("Job not found");
    }

    @Override
    public Job deactivateJobById(Long id) {
        Optional<Job> jobContainer = jobRepository.findById(id);
        if(jobContainer.isPresent()) {
            Job _job = jobContainer.get();
            _job.setIsActive(false);

            return jobRepository.save(_job);
        }
        else throw new JobNotFoundException("Job not found");
    }

    @Override
    public Job deleteJobById(Long id) {
        Optional<Job> jobContainer = jobRepository.findById(id);
        if(jobContainer.isPresent()) {
            Job _job = jobContainer.get();
            _job.setIsDeleted(true);
            return jobRepository.save(_job);
        }
        else throw new JobNotFoundException("Job not found");
    }

    @Override
    public Job deleteJobPremanentlyById(Long id) {
        Optional<Job> jobContainer = jobRepository.findById(id);
        if(jobContainer.isPresent()) {
            Job _job = jobContainer.get();
            jobRepository.delete(_job);
            return _job;
        }
        else throw new JobNotFoundException("Job not found");
    }

    @Override
    public Job purgeExpiredJobs() {
        return null;
    }
}
