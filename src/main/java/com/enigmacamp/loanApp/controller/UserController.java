package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.dto.response.CustomerResponse;
import com.enigmacamp.loanApp.model.dto.response.UserResponse;
import com.enigmacamp.loanApp.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@SecurityRequirement(name = "Authorization")
public class UserController {
    private final UserService userService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<UserResponse>> getUserById(@PathVariable String id) {
        UserResponse userResponse = userService.getUserById(id);
        CommonResponse<UserResponse> response = CommonResponse.<UserResponse>builder()
                .message("Successfully get all user")
                .data(Optional.of(userResponse))
                .build();
        return ResponseEntity.ok(response);
    }
}
