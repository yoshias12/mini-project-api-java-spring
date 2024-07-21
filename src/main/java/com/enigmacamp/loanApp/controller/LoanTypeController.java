package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.dto.response.LoanTypeResponse;
import com.enigmacamp.loanApp.model.entity.LoanType;
import com.enigmacamp.loanApp.service.LoanTypeService;
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
@RequestMapping("/api/loan-types")
public class LoanTypeController {
    private final LoanTypeService loanTypeService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<CommonResponse<LoanTypeResponse>> createLoanType (@RequestBody LoanType loanType){
        LoanTypeResponse createLoantType = loanTypeService.createLoanType(loanType);
        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .message("Success creating loan type")
                .data(Optional.of(createLoantType))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanType>> getLoanById (@PathVariable String id) {
        LoanType createLoantType = loanTypeService.getLoanTypeById(id);
        CommonResponse<LoanType> commonResponse = CommonResponse.<LoanType>builder()
                .message("Success getting loan type by id " + id)
                .data(Optional.of(createLoantType))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<CommonResponse<List<LoanTypeResponse>>> getAllLoanTypes() {
        List<LoanTypeResponse> loanTypes = loanTypeService.getAllLoanType();
        CommonResponse<List<LoanTypeResponse>> commonResponse = CommonResponse.<List<LoanTypeResponse>>builder()
                .message("Success getting all loan types")
                .data(Optional.of(loanTypes))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @PutMapping
    public ResponseEntity<CommonResponse<LoanTypeResponse>> updateLoanType (@RequestBody LoanType loanType) {
        LoanTypeResponse updatedLoanType = loanTypeService.updateLoanType(loanType);
        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .message("Success updating loan type")
                .data(Optional.of(updatedLoanType))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteLoanType (@PathVariable String id) {
        loanTypeService.deleteLoanType(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .message("Success deleting loan type by id " + id)
                .build();
        return ResponseEntity.ok(commonResponse);
    }

}
