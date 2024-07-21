package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.request.AuthRequest;
import com.enigmacamp.loanApp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.dto.response.LoginResponse;
import com.enigmacamp.loanApp.model.dto.response.RegisterResponse;
import com.enigmacamp.loanApp.model.entity.Staff;
import com.enigmacamp.loanApp.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerCustomers(@RequestBody AuthRequest<CustomerRequest> request) {
        RegisterResponse response = authService.registerCustomerUser(request);

        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .message("Successfully register new Account")
                .data(Optional.of(response))
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody AuthRequest<String> request) {
        LoginResponse response = authService.login(request);

        CommonResponse<LoginResponse> commonResponse = CommonResponse.<LoginResponse>builder()
                .message("Login successful")
                .data(Optional.of(response))
                .build();

        return ResponseEntity.ok(commonResponse);
    }
}
