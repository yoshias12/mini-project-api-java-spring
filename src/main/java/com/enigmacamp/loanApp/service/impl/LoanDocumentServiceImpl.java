package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.model.entity.Customer;
import com.enigmacamp.loanApp.model.entity.LoanDocument;
import com.enigmacamp.loanApp.repository.LoanDocumentRepository;
import com.enigmacamp.loanApp.service.CustomerService;
import com.enigmacamp.loanApp.service.LoanDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanDocumentServiceImpl implements LoanDocumentService {
    private final LoanDocumentRepository loanDocumentRepository;
    private final CustomerService customerService;

    @Override
    public LoanDocument createLoanDocument(String id, LoanDocument loanDocument) {
        Customer customer = customerService.getCustomerById(id);

        return loanDocumentRepository.saveAndFlush(LoanDocument.builder()
                .contentType(loanDocument.getContentType())
                .name(loanDocument.getName())
                .path(loanDocument.getPath())
                .size(loanDocument.getSize())
                .customerId(customer)
                .build());
    }

    @Override
    public List<LoanDocument> getAllLoanDocument() {
        return loanDocumentRepository.findAll();
    }

    @Override
    public LoanDocument updateLoanDocument(LoanDocument loanDocument) {
        getLoanDocumentsById(loanDocument.getId());
        return loanDocumentRepository.saveAndFlush(LoanDocument.builder()
                .contentType(loanDocument.getContentType())
                .name(loanDocument.getName())
                .path(loanDocument.getPath())
                .size(loanDocument.getSize())
                .customerId(loanDocument.getCustomerId())
                .build());
    }

    @Override
    public LoanDocument getLoanDocumentsById(String id) {
        return loanDocumentRepository.findById(id).orElseThrow(()-> new RuntimeException("Loan document not found"));
    }

    @Override
    public void deleteLoanDocument(String id) {
        LoanDocument loanDocument = getLoanDocumentsById(id);
        loanDocumentRepository.delete(loanDocument);
    }
}
