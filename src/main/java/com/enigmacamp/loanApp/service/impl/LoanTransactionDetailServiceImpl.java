package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.model.entity.LoanTransactionDetail;
import com.enigmacamp.loanApp.repository.LoanTransactionDetailRepository;
import com.enigmacamp.loanApp.service.LoanTransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanTransactionDetailServiceImpl implements LoanTransactionDetailService {
    private final LoanTransactionDetailRepository loanTransactionDetailRepository;

    @Override
    public LoanTransactionDetail findById(String id) {
        return loanTransactionDetailRepository.findById(id).orElseThrow(()-> new RuntimeException("Loan transaction detail not found"));
    }
}
