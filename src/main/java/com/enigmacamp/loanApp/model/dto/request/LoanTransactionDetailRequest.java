package com.enigmacamp.loanApp.model.dto.request;

import com.enigmacamp.loanApp.model.entity.LoanTransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionDetailRequest {
    private String id;
    private Long transactionDate;
    private Double nominal;
    private String loanStatus;
    private Long createdAt;
    private Long updatedAt;
}
