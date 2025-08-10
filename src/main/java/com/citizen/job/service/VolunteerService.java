package com.citizen.job.service;

import com.citizen.job.entity.Volunteer;

import java.util.List;

public interface VolunteerService {

    Volunteer saveVolunteer(Volunteer volunteer);

    Volunteer findVolunteerById(Long id);

    List<Volunteer> findAllVolunteers();

    List<Volunteer> findAllActiveVolunteers();

    List<Volunteer> findAllInactiveVolunteers();

    Volunteer updateVolunteerById(Long id, Volunteer volunteer);

    Volunteer activateVolunteerById(Long id);

    Volunteer deactivateVolunteerById(Long id);

    Volunteer deleteVolunteerById(Long id);

    Volunteer deleteVolunteerPermanentlyById(Long id);

    Volunteer purgeExpiredVolunteers();

}
