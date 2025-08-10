package com.citizen.job.controller;

import com.citizen.job.client.UserInterface;
import com.citizen.job.dto.EmailResponseDto;
import com.citizen.job.dto.TokenResponseDto;
import com.citizen.job.entity.Volunteer;
import com.citizen.job.response.ApiResponse;
import com.citizen.job.service.VolunteerService;
import com.citizen.job.utils.UserUnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/work")
public class VolunteerController {

    private final VolunteerService volunteerService;
    private final UserInterface userInterface;

    public VolunteerController(@Autowired VolunteerService volunteerService, @Autowired UserInterface userInterface) {
        this.volunteerService = volunteerService;
        this.userInterface = userInterface;
    }

    @PostMapping("/service")
    public ResponseEntity<ApiResponse<Volunteer>> addVolunteer(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @RequestBody Volunteer job) {

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        Volunteer saveVolunteer = volunteerService.saveVolunteer(job);
        ApiResponse<Volunteer> response = ApiResponse.success(saveVolunteer, "Volunteer added");
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/service")
    public ResponseEntity<ApiResponse<List<Volunteer>>> getAllVolunteers(
            @RequestHeader("id") Long citizenId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token){

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        List<Volunteer> _jobs = volunteerService.findAllVolunteers();
        ApiResponse<List<Volunteer>> response = ApiResponse.success(_jobs, "List of All Volunteers");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<ApiResponse<Volunteer>> findVolunteerById(
            @RequestHeader("id") Long citizenId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id) {

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        Volunteer updatedVolunteer = volunteerService.findVolunteerById(id);
        ApiResponse<Volunteer> response = ApiResponse.success(updatedVolunteer, "Volunteer by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/service/{id}")
    public ResponseEntity<ApiResponse<Volunteer>> updateVolunteer(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id,
            @RequestBody Volunteer newVolunteer) {

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        Volunteer updatedVolunteer = volunteerService.updateVolunteerById(id, newVolunteer);
        ApiResponse<Volunteer> response = ApiResponse.success(updatedVolunteer, "Volunteer updated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<ApiResponse<Volunteer>> deleteVolunteer(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id) {

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        Volunteer _job = volunteerService.deleteVolunteerById(id);
        ApiResponse<Volunteer> response = ApiResponse.success(_job, "Volunteer deleted successfully with ID: " + id);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/service/delete/{id}")
    public ResponseEntity<ApiResponse<Volunteer>> deleteVolunteerPermanently(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id) {

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        Volunteer _job = volunteerService.deleteVolunteerPermanentlyById(id);
        ApiResponse<Volunteer> response = ApiResponse.success(_job, "Volunteer deleted successfully with ID: " + id);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/service/active")
    public ResponseEntity<ApiResponse<List<Volunteer>>> getActiveVolunteers(
            @RequestHeader("id") Long citizenId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token) {

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        List<Volunteer> _jobs = volunteerService.findAllActiveVolunteers();
        ApiResponse<List<Volunteer>> response = ApiResponse.success(_jobs, "List of Active Volunteers");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/service/inactive")
    public ResponseEntity<ApiResponse<List<Volunteer>>> getInactiveVolunteers(
            @RequestHeader("id") Long citizenId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token) {

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        List<Volunteer> _jobs = volunteerService.findAllInactiveVolunteers();
        ApiResponse<List<Volunteer>> response = ApiResponse.success(_jobs, "List of Inactive Volunteers");
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/service/{id}/activate")
    public ResponseEntity<ApiResponse<Volunteer>> activateVolunteer(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id) {

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        Volunteer _job = volunteerService.activateVolunteerById(id);
        ApiResponse<Volunteer> response = ApiResponse.success(_job, "Volunteer activated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/service/{id}/deactivate")
    public ResponseEntity<ApiResponse<Volunteer>> deactivateVolunteer(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token,
            @PathVariable Long id) {

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        Volunteer _job = volunteerService.deactivateVolunteerById(id);
        ApiResponse<Volunteer> response = ApiResponse.success(_job, "Volunteer deactivated by id:"+id);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/service/purge-expired")
    public ResponseEntity<ApiResponse<Volunteer>> purgeExpiredVolunteers(
            @RequestHeader("id") Long adminId,
            @RequestHeader("email") String email,
            @RequestHeader("token") String token) {

        TokenResponseDto tokenResponseDto = userInterface.validateUser(token).getBody();
        assert tokenResponseDto != null;
        if(!tokenResponseDto.isValid()){
            throw new UserUnauthorizedException("User is not authorized");
        }

        EmailResponseDto emailResponseDto = userInterface.getProfileByEmail(email).getBody().getData();

        if (!emailResponseDto.getEmail().equals(email)) {
            throw new UserUnauthorizedException("User is not authorized");
        }

        Volunteer _job = volunteerService.purgeExpiredVolunteers();
        ApiResponse<Volunteer> response = ApiResponse.success(_job, "Volunteer deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

