package com.citizen.job.service.impl;

import com.citizen.job.entity.Volunteer;
import com.citizen.job.repository.VolunteerRepository;
import com.citizen.job.service.VolunteerService;
import com.citizen.job.utils.VolunteerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    VolunteerRepository volunteerRepository;

    private VolunteerServiceImpl(@Autowired VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Volunteer saveVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer findVolunteerById(Long id) {
        Optional<Volunteer> volunteerContainer = volunteerRepository.findById(id);
        if(volunteerContainer.isPresent()) {
            return volunteerContainer.get();
        }
        else throw new VolunteerNotFoundException("Job not found");
    }

    @Override
    public List<Volunteer> findAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @Override
    public List<Volunteer> findAllActiveVolunteers() {
        return volunteerRepository.findAllByIsActive(true);
    }

    @Override
    public List<Volunteer> findAllInactiveVolunteers() {
        return volunteerRepository.findAllByIsActive(false);
    }

    @Override
    public Volunteer updateVolunteerById(Long id, Volunteer volunteer) {
        Optional<Volunteer> volunteerContainer = volunteerRepository.findById(id);
        if(volunteerContainer.isPresent()) {
            Volunteer _volunteer = volunteerContainer.get();
            _volunteer.setProgramTitle(volunteer.getProgramTitle());
            _volunteer.setLocation(volunteer.getLocation());
            _volunteer.setProgramDescription(volunteer.getProgramDescription());
            _volunteer.setProgramDate(volunteer.getProgramDate());
            _volunteer.setProgramTime(volunteer.getProgramTime());
            _volunteer.setDuration(volunteer.getDuration());
            _volunteer.setCoordinatorName(volunteer.getCoordinatorName());
            _volunteer.setCoordinatorEmail(volunteer.getCoordinatorEmail());
            _volunteer.setCoordinatorPhone(volunteer.getCoordinatorPhone());
            _volunteer.setCoordinatorAddress(volunteer.getCoordinatorAddress());
            _volunteer.setIsActive(volunteer.getIsActive());
            _volunteer.setIsDeleted(volunteer.getIsDeleted());
            return volunteerRepository.save(_volunteer);
        }
        else throw new VolunteerNotFoundException("Volunteer Post not found");
    }

    @Override
    public Volunteer activateVolunteerById(Long id) {
        Optional<Volunteer> volunteerContainer = volunteerRepository.findById(id);
        if(volunteerContainer.isPresent()) {
            Volunteer _volunteer = volunteerContainer.get();
            _volunteer.setIsActive(true);
            return volunteerRepository.save(_volunteer);
        }
        else throw new VolunteerNotFoundException("Volunteer Post not found");
    }

    @Override
    public Volunteer deactivateVolunteerById(Long id) {
        Optional<Volunteer> volunteerContainer = volunteerRepository.findById(id);
        if(volunteerContainer.isPresent()) {
            Volunteer _volunteer = volunteerContainer.get();
            _volunteer.setIsActive(false);
            return volunteerRepository.save(_volunteer);
        }
        else throw new VolunteerNotFoundException("Volunteer Post not found");
    }

    @Override
    public Volunteer deleteVolunteerById(Long id) {
        Optional<Volunteer> volunteerContainer = volunteerRepository.findById(id);
        if(volunteerContainer.isPresent()) {
            Volunteer _volunteer = volunteerContainer.get();
            _volunteer.setIsDeleted(true);
            return volunteerRepository.save(_volunteer);
        }
        else throw new VolunteerNotFoundException("Volunteer Post not found");
    }

    @Override
    public Volunteer purgeExpiredVolunteers() {
        return null;
    }
}
