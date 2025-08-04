package com.citizen.job.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
    private String fullName;
    private String department;
    private String contactNumber;
    private String emailAddress;
    private String password;
    private String fullAddress;
    private LocalDate dob;
    private String aadharNumber;
}