package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.entity.LoanDocument;

import java.util.List;

public interface LoanDocumentService {
    LoanDocument createLoanDocument(String id,LoanDocument loanDocument);
    List<LoanDocument> getAllLoanDocument();
    LoanDocument updateLoanDocument(LoanDocument loanDocument);
    LoanDocument getLoanDocumentsById(String id);
    void deleteLoanDocument(String id);
}
