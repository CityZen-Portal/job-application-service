package com.citizen.job.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private boolean valid;
    private String email;
    private String message;
//    private String userId;
//    private String role;
}