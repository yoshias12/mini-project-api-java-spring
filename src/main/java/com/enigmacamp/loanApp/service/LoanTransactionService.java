package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.dto.request.LoanTransactionApprovedRequest;
import com.enigmacamp.loanApp.model.dto.request.LoanTransactionRequest;
import com.enigmacamp.loanApp.model.dto.request.PaymentResponse;
import com.enigmacamp.loanApp.model.dto.response.LoanTransactionResponse;

public interface LoanTransactionService {
    LoanTransactionResponse createLoanTransaction(LoanTransactionRequest request);
    LoanTransactionResponse getTransactionById(String id);
    LoanTransactionResponse approveTransactionRequestByAdminId(String adminId,LoanTransactionApprovedRequest request);
    PaymentResponse addPayment(String trxId);
}
