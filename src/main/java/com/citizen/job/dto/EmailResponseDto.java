package com.citizen.job.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponseDto {
    private Long id;
    private String email;
    private String userName;
    private Set<RoleDto> roles;
    private String aadhaar;
}