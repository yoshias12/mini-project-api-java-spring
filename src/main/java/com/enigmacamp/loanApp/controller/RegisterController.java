package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.request.AuthRequest;
import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.dto.response.RegisterResponse;
import com.enigmacamp.loanApp.model.entity.Admin;
import com.enigmacamp.loanApp.model.entity.Staff;
import com.enigmacamp.loanApp.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/register")
public class RegisterController {
    private final AuthService authService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/staff/signup")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerStaff(@RequestBody AuthRequest<Staff> request) {
        RegisterResponse response = authService.registerStaffUser(request);

        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .message("Successfully register new Account")
                .data(Optional.of(response))
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/admin/signup")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerAdmin(@RequestBody AuthRequest<Admin> request) {
        RegisterResponse response = authService.registerAdminUser(request);

        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .message("Successfully register new Account")
                .data(Optional.of(response))
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }
}
