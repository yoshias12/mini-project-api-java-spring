package com.enigmacamp.loanApp.model.dto.request;

import com.enigmacamp.loanApp.model.dto.response.LoanTransactionDetailResponses;
import com.enigmacamp.loanApp.model.entity.Customer;
import com.enigmacamp.loanApp.model.entity.InstalmentType;
import com.enigmacamp.loanApp.model.entity.LoanTransaction;
import com.enigmacamp.loanApp.model.entity.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionRequest {
    private LoanTypeIdRequest loanType;
    private InstalmentTypeIdRequest instalmentType;
    private CustomerIdRequest customer;
    private Long nominal;
}
