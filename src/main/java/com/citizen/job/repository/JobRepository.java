package com.citizen.job.repository;

import com.citizen.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findAllByIsActive(boolean active);

    Job findByIdAndIsActive(Long id, boolean b);

    Job findByIdAndIsDeleted(Long id, boolean b);

    List<Job> findAllByIsActiveAndIsDeleted(boolean b, boolean b1);

    Job findByIdAndIsActiveAndIsDeleted(Long id, boolean b, boolean b1);
}
