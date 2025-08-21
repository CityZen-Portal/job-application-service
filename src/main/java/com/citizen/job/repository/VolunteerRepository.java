package com.citizen.job.repository;

import com.citizen.job.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer,Long> {
    List<Volunteer> findAllByIsActive(boolean active);

    Volunteer findByIdAndIsActive(Long id, boolean b);

    List<Volunteer> findAllByIsActiveAndIsDeleted(boolean b, boolean b1);

    Volunteer findByIdAndIsDeleted(Long id, boolean b);

    Volunteer findByIdAndIsActiveAndIsDeleted(Long id, boolean b, boolean b1);
}
