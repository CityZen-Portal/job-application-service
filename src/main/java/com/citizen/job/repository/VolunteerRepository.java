package com.citizen.job.repository;

import com.citizen.job.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer,Long> {
    List<Volunteer> findAllByIsActive(boolean active);
}
