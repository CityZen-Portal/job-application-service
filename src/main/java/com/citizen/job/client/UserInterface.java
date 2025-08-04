package com.citizen.job.client;

import com.citizen.job.dto.ApiResponseDto;
import com.citizen.job.dto.EmailResponseDto;
import com.citizen.job.dto.TokenResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "UserManagementService", url = "https://auth-backend-obcu.onrender.com")
public interface UserInterface {
    @GetMapping("/api/auth/validate")
    ResponseEntity<TokenResponseDto> validateUser(@RequestHeader("token")  String token);

    @GetMapping("/api/auth/getUser/{email}")
    ResponseEntity<ApiResponseDto<EmailResponseDto>> getProfileByEmail(@PathVariable("email") String emailId
    );
}