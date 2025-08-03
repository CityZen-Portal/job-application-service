package com.citizen.job.controller;

import com.citizen.job.entity.Volunteer;
import com.citizen.job.response.ApiResponse;
import com.citizen.job.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/work")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(@Autowired VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping("/service")
    public ResponseEntity<ApiResponse<Volunteer>> addVolunteer(@RequestBody Volunteer job) {
        Volunteer saveVolunteer = volunteerService.saveVolunteer(job);
        ApiResponse<Volunteer> response = ApiResponse.success(saveVolunteer, "Volunteer added");
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/service")
    public ResponseEntity<ApiResponse<List<Volunteer>>> getAllVolunteers(){
        List<Volunteer> _jobs = volunteerService.findAllVolunteers();
        ApiResponse<List<Volunteer>> response = ApiResponse.success(_jobs, "List of All Volunteers");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<ApiResponse<Volunteer>> findVolunteerById(@PathVariable Long id) {
        Volunteer updatedVolunteer = volunteerService.findVolunteerById(id);
        ApiResponse<Volunteer> response = ApiResponse.success(updatedVolunteer, "Volunteer by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/service/{id}")
    public ResponseEntity<ApiResponse<Volunteer>> updateVolunteer(@PathVariable Long id, @RequestBody Volunteer newVolunteer) {
        Volunteer updatedVolunteer = volunteerService.updateVolunteerById(id, newVolunteer);
        ApiResponse<Volunteer> response = ApiResponse.success(updatedVolunteer, "Volunteer updated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<ApiResponse<Volunteer>> deleteVolunteer(@PathVariable Long id) {
        Volunteer _job = volunteerService.deleteVolunteerById(id);
        ApiResponse<Volunteer> response = ApiResponse.success(_job, "Volunteer deleted successfully with ID: " + id);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/service/active")
    public ResponseEntity<ApiResponse<List<Volunteer>>> getActiveVolunteers() {
        List<Volunteer> _jobs = volunteerService.findAllActiveVolunteers();
        ApiResponse<List<Volunteer>> response = ApiResponse.success(_jobs, "List of Active Volunteers");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/service/inactive")
    public ResponseEntity<ApiResponse<List<Volunteer>>> getInactiveVolunteers() {
        List<Volunteer> _jobs = volunteerService.findAllInactiveVolunteers();
        ApiResponse<List<Volunteer>> response = ApiResponse.success(_jobs, "List of Inactive Volunteers");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/service/{id}/activate")
    public ResponseEntity<ApiResponse<Volunteer>> activateVolunteer(@PathVariable Long id) {
        Volunteer _job = volunteerService.activateVolunteerById(id);
        ApiResponse<Volunteer> response = ApiResponse.success(_job, "Volunteer activated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/service/{id}/deactivate")
    public ResponseEntity<ApiResponse<Volunteer>> deactivateVolunteer(@PathVariable Long id) {
        Volunteer _job = volunteerService.deactivateVolunteerById(id);
        ApiResponse<Volunteer> response = ApiResponse.success(_job, "Volunteer deactivated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/service/purge-expired")
    public ResponseEntity<ApiResponse<Volunteer>> purgeExpiredVolunteers() {
        Volunteer _job = volunteerService.purgeExpiredVolunteers();
        ApiResponse<Volunteer> response = ApiResponse.success(_job, "Volunteer deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

