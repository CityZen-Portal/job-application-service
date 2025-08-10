package com.citizen.job.service;

import com.citizen.job.dto.OpportunitiesCountDto;
import com.citizen.job.entity.Job;

import java.util.List;

public interface JobService {

    Job saveJob(Job job);

    Job findJobById(Long id);

    List<Job> findAllJobs();

    List<Job> findAllActiveJobs();

    OpportunitiesCountDto getOpportunitiesCount();

    List<Job> findAllInactiveJobs();

    Job updateJobById(Long id, Job job);

    Job activateJobById(Long id);

    Job deactivateJobById(Long id);

    Job deleteJobById(Long id);

    Job deleteJobPremanentlyById(Long id);

    Job purgeExpiredJobs();
}
