package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.request.LoanTransactionApprovedRequest;
import com.enigmacamp.loanApp.model.dto.request.LoanTransactionRequest;
import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.dto.response.LoanTransactionResponse;
import com.enigmacamp.loanApp.service.LoanTransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/transactions")
public class LoanTransactionController {
    private final LoanTransactionService loanTransactionService;
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @PostMapping
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> createRequestLoan(@RequestBody LoanTransactionRequest request) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.createLoanTransaction(request);
        CommonResponse<LoanTransactionResponse> commonResponse = CommonResponse.<LoanTransactionResponse>builder()
                .message("Success creating loan transaction")
                .data(Optional.of(loanTransactionResponse))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> getLoanTransactionById(@PathVariable String id) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.getTransactionById(id);
        CommonResponse<LoanTransactionResponse> commonResponse = CommonResponse.<LoanTransactionResponse>builder()
                .message("Success getting loan transaction by id = " + id)
                .data(Optional.of(loanTransactionResponse))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @PutMapping("/{adminId}/approve")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> approveLoanTransactionRequestByAdmin(@PathVariable String adminId, @RequestBody LoanTransactionApprovedRequest request) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.approveTransactionRequestByAdminId(adminId,request);
        CommonResponse<LoanTransactionResponse> commonResponse = CommonResponse.<LoanTransactionResponse>builder()
                .message("Success approving loan transaction by admin id = " + adminId)
                .data(Optional.of(loanTransactionResponse))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
}
