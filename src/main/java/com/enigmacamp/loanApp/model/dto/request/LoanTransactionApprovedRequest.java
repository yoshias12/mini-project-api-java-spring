package com.enigmacamp.loanApp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionApprovedRequest {
    private String loanTransactionId;
    private Integer interestRates;
}
