package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.entity.InstalmentType;
import com.enigmacamp.loanApp.service.InstalmentTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/instalment-types")
public class InstalmentTypeController {
    private final InstalmentTypeService instalmentTypeService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<CommonResponse<InstalmentType>> createInstalmentType(@RequestBody InstalmentType instalmentType) {
        InstalmentType createdInstalmentType = instalmentTypeService.create(instalmentType);
        CommonResponse<InstalmentType> commonResponse = CommonResponse.<InstalmentType>builder()
                .message("created")
                .data(Optional.of(createdInstalmentType))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<InstalmentType>> getById(@PathVariable String id) {
        InstalmentType instalmentType = instalmentTypeService.getById(id);
        CommonResponse<InstalmentType> commonResponse = CommonResponse.<InstalmentType>builder()
                .message("Succesfully get intalment type by id = " + id)
                .data(Optional.of(instalmentType))
                .build();

        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<CommonResponse<List<InstalmentType>>> getAllInstalmentsType() {
        List<InstalmentType> instalmentTypes = instalmentTypeService.getAllInstalments();
        CommonResponse<List<InstalmentType>> response = CommonResponse.<List<InstalmentType>>builder()
                .message("Successfully get all intalments types")
                .data(Optional.of(instalmentTypes))
                .build();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @PutMapping
    public ResponseEntity<CommonResponse<InstalmentType>> updateInstalmentType(@RequestBody InstalmentType instalmentType) {
        InstalmentType updateInstalmentType = instalmentTypeService.update(instalmentType);
        CommonResponse<InstalmentType> commonResponse = CommonResponse.<InstalmentType>builder()
                .message("updated successfully")
                .data(Optional.of(updateInstalmentType))
                .build();

        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteInstalmentType(@PathVariable String id) {
        instalmentTypeService.delete(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .message("Deleted successfully")
                .build();

        return ResponseEntity.ok(commonResponse);
    }
}
