package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.model.dto.response.LoanTypeResponse;
import com.enigmacamp.loanApp.model.entity.LoanType;
import com.enigmacamp.loanApp.repository.LoanTypeRepository;
import com.enigmacamp.loanApp.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoantTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;

    @Override
    public LoanTypeResponse createLoanType(LoanType loan) {
        LoanType loanType = loanTypeRepository.saveAndFlush(LoanType.builder()
                .type(loan.getType())
                .maxLoan(loan.getMaxLoan())
                .build()
        );
        return convertLoanTypeResponse(loanType);
    }

    @Override
    public LoanType getLoanTypeById(String id) {
        return findByidOrThrowNotFound(id);
    }

    @Override
    public List<LoanTypeResponse> getAllLoanType() {
        List<LoanType> loanTypes = loanTypeRepository.findAll();
        return loanTypes.stream()
                .map(this::convertLoanTypeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanTypeResponse updateLoanType(LoanType loan) {
        findByidOrThrowNotFound(loan.getId());

        LoanType loanType = loanTypeRepository.saveAndFlush(LoanType.builder()
                .id(loan.getId())
                .type(loan.getType())
                .maxLoan(loan.getMaxLoan())
                .build()
        );
        return convertLoanTypeResponse(loanType);

    }

    @Override
    public void deleteLoanType(String id) {
        findByidOrThrowNotFound(id);
        loanTypeRepository.deleteById(id);
    }

    private LoanType findByidOrThrowNotFound(String id) {
        return loanTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan type not found"));
    }

    private LoanTypeResponse convertLoanTypeResponse(LoanType loanType) {
        return LoanTypeResponse.builder()
                .id(loanType.getId())
                .type(loanType.getType())
                .maxLoan((int) Math.round(loanType.getMaxLoan()))
                .build();
    }
}
