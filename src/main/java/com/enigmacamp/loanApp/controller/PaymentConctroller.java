package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.request.PaymentResponse;
import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.service.LoanTransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/transactions")
public class PaymentConctroller {
    private final LoanTransactionService loanTransactionService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    @PutMapping("/{trxId}/pay")
    public ResponseEntity<CommonResponse<PaymentResponse>> payment(@PathVariable String trxId){
        PaymentResponse paymentResponse = loanTransactionService.addPayment(trxId);
        CommonResponse<PaymentResponse> commonResponse = CommonResponse.<PaymentResponse>builder()
               .message("Payment Successful")
               .data(Optional.of(paymentResponse))
               .build();
        return ResponseEntity.ok(commonResponse);  // return payment response to client
    }
}

