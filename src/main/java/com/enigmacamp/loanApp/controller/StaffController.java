package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.entity.Staff;
import com.enigmacamp.loanApp.service.StaffService;
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
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffService staffService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Staff>> getStaffById(@PathVariable String id) {
        Staff staff = staffService.getStaffById(id);
        CommonResponse<Staff> response = CommonResponse.<Staff>builder()
                .message("Successfully fetch user")
                .data(Optional.of(staff))
                .build();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<CommonResponse<List<Staff>>> getAllCustomer(){
        List<Staff> staff = staffService.getAllStaff();
        CommonResponse<List<Staff>> response = CommonResponse.<List<Staff>>builder()
                .message("Successfully fetch user")
                .data(Optional.of(staff))
                .build();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Staff> updateCustomer(@RequestBody Staff request){
        Staff staff = staffService.updateStaff(request);
        return ResponseEntity.ok(staff);
    }
}
