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
    private String username;
    private Set<RoleDto> role;
    private String aadharNumber;
}