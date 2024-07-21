package com.enigmacamp.loanApp.model.dto.response;

import com.enigmacamp.loanApp.model.entity.LoanTransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionDetailResponses {
    private String id;
    private Long transactionDate;
    private Long nominal;
    private LoanTransactionDetail.LoanStatus loanStatus;
    private Long createdAt;
    private Long updatedAt;
}
