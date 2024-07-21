package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.dto.response.LoanTypeResponse;
import com.enigmacamp.loanApp.model.entity.LoanType;

import java.util.List;

public interface LoanTypeService {
    LoanTypeResponse createLoanType(LoanType loan);
    LoanType getLoanTypeById(String id);
    List<LoanTypeResponse> getAllLoanType();
    LoanTypeResponse updateLoanType(LoanType loan);
    void deleteLoanType(String id);
}
