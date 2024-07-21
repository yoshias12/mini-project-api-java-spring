package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.entity.LoanDocument;
import com.enigmacamp.loanApp.service.LoanDocumentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/loan-documents")
public class LoanDocumentController {
    private final LoanDocumentService loanDocumentService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    @PostMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanDocument>> createLoanDocument(@PathVariable String id, @RequestBody LoanDocument request) {
        LoanDocument loanDocument = loanDocumentService.createLoanDocument(id,request);
        CommonResponse<LoanDocument> commonResponse = CommonResponse.<LoanDocument>builder()
                .message("Success creating loan document")
                .data(Optional.of(loanDocument))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<CommonResponse<Iterable<LoanDocument>>> getAllLoanDocuments() {
        Iterable<LoanDocument> loanDocuments = loanDocumentService.getAllLoanDocument();
        CommonResponse<Iterable<LoanDocument>> commonResponse = CommonResponse.<Iterable<LoanDocument>>builder()
                .message("Success getting all loan documents")
                .data(Optional.of(loanDocuments))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanDocument>> getLoanDocumentById(@PathVariable String id) {
        LoanDocument loanDocument = loanDocumentService.getLoanDocumentsById(id);
        CommonResponse<LoanDocument> commonResponse = CommonResponse.<LoanDocument>builder()
                .message("Success getting loan document by id = " + id)
                .data(Optional.of(loanDocument))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    @PutMapping
    public ResponseEntity<CommonResponse<LoanDocument>> updateLoanDocument(@RequestBody LoanDocument request) {
        LoanDocument loanDocument = loanDocumentService.updateLoanDocument(request);
        CommonResponse<LoanDocument> commonResponse = CommonResponse.<LoanDocument>builder()
                .message("Success updating loan document")
                .data(Optional.of(loanDocument))
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteLoanDocument(@PathVariable String id) {
        loanDocumentService.deleteLoanDocument(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .message("Deleted successfully")
                .build();
        return ResponseEntity.ok(commonResponse);
    }
}
