package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.entity.Admin;
import com.enigmacamp.loanApp.service.AdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Admin>> getAdminById(@PathVariable String id) {
        Admin admin = adminService.getAdminById(id);
        CommonResponse<Admin> response = CommonResponse.<Admin>builder()
                .message("Successfully fetch user")
                .data(Optional.of(admin))
                .build();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<CommonResponse<List<Admin>>> getAllAdmin(){
        List<Admin> admin = adminService.getAllAdmin();
        CommonResponse<List<Admin>> response = CommonResponse.<List<Admin>>builder()
                .message("Successfully fetch user")
                .data(Optional.of(admin))
                .build();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin request){
        Admin admin = adminService.updateAdmin(request);
        return ResponseEntity.ok(admin);
    }
}
