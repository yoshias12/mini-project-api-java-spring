package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.model.dto.request.*;
import com.enigmacamp.loanApp.model.dto.response.LoanTransactionResponse;
import com.enigmacamp.loanApp.model.dto.response.UserResponse;
import com.enigmacamp.loanApp.model.entity.*;
import com.enigmacamp.loanApp.repository.LoanTransactionDetailRepository;
import com.enigmacamp.loanApp.repository.LoanTransactionRepository;
import com.enigmacamp.loanApp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoanTransactionServiceImpl implements LoanTransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanTransactionDetailRepository loanTransactionDetailRepository;
    private final CustomerService customerService;
    private final InstalmentTypeService instalmentTypeService;
    private final LoanTypeService loanTypeService;
    private final UserService userService;
    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public LoanTransactionResponse createLoanTransaction(LoanTransactionRequest request) {
        LoanType loanTypeById = loanTypeService.getLoanTypeById(request.getLoanType().getId());
        InstalmentType instalmentTypeId = instalmentTypeService.getById(request.getInstalmentType().getId());
        Customer customerId = customerService.getCustomerById(request.getCustomer().getId());

        LoanTransaction loanTransaction = LoanTransaction.builder()
                .loanType(loanTypeById)
                .instalmentType(instalmentTypeId)
                .customer(customerId)
                .nominal(Double.valueOf(request.getNominal()))
                .loanTransactionDetails(null)
                .createdAt(System.currentTimeMillis())
                .build();


//        LoanTransactionDetail loanTransactionDetail = LoanTransactionDetail.builder()
//                .loanStatus(LoanTransactionDetail.LoanStatus.UNPAID)
//                .transactionDate(date)
//                .nominal(Double.valueOf(request.getNominal()))
//                .createdAt(date)
//                .loanTransaction(loanTransaction)
//                .build();
//        loanTransaction.setLoanTransactionDetails(List.of(loanTransactionDetail));
//        loanTransactionDetail.setLoanTransaction(loanTransaction);

        loanTransactionRepository.saveAndFlush(loanTransaction);
//        loanTransactionDetailRepository.saveAndFlush(loanTransactionDetail);
        return convertToLoanTransactionResponse(loanTransaction);
    }

    @Override
    public LoanTransactionResponse getTransactionById(String id) {
        return convertToLoanTransactionResponse(findByIdOrThrowNotFound(id));
    }

    @Override
    public LoanTransactionResponse approveTransactionRequestByAdminId(String adminId, LoanTransactionApprovedRequest request) {
        UserResponse userResponse = userService.getUserById(adminId);
        LoanTransaction loanTransactionRequest = findByIdOrThrowNotFound(request.getLoanTransactionId());
        var nominal = loanTransactionRequest.getNominal() + (loanTransactionRequest.getNominal() * (request.getInterestRates() / 100.0));


        LoanTransactionDetail loanTransactionDetail = LoanTransactionDetail.builder()
                .loanStatus(LoanTransactionDetail.LoanStatus.UNPAID)
                .transactionDate(System.currentTimeMillis())
                .nominal(nominal)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .loanTransaction(loanTransactionRequest)
                .build();


        LoanTransaction loanTransaction = LoanTransaction.builder()
                .loanType(loanTransactionRequest.getLoanType())
                .instalmentType(loanTransactionRequest.getInstalmentType())
                .customer(loanTransactionRequest.getCustomer())
                .nominal(loanTransactionRequest.getNominal())
                .approvedAt(System.currentTimeMillis())
                .approvedBy(userResponse.getEmail())
                .approvalStatus(LoanTransaction.ApprovalStatus.APPROVED)
                .loanTransactionDetails(List.of(loanTransactionDetail))
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .build();
        loanTransactionDetailRepository.saveAndFlush(loanTransactionDetail);
        loanTransactionRepository.saveAndFlush(loanTransaction);

        return convertToLoanTransactionResponse(loanTransaction);
    }

    @Override
    public PaymentResponse addPayment(String trxId) {
        LoanTransactionDetail loanTransactionDetail = findByIdTrxDetailOrThrowNotFound(trxId);

        //URL
        String url = "https://app.sandbox.midtrans.com/snap/v1/transactions";

        //Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic U0ItTWlkLXNlcnZlci1CcVJOVnY0aFhLOW9xTUVuNk1QVXR6Tlg6");

        //Payment Request (payload)
        PaymentDetailRequest paymentDetailRequest = PaymentDetailRequest.builder()
                .orderId(loanTransactionDetail.getId())
                .amount(loanTransactionDetail.getNominal())
                .build();
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .paymentDetail(paymentDetailRequest)
                .build();

        //Http Entity
        HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest, headers);

        // Send Request
        ResponseEntity<PaymentResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, PaymentResponse.class);

        loanTransactionDetail.setLoanStatus(LoanTransactionDetail.LoanStatus.PAID);
        loanTransactionDetailRepository.saveAndFlush(loanTransactionDetail);
        return PaymentResponse.builder()
                .token(Objects.requireNonNull(response.getBody()).getToken())
                .redirectUrl(Objects.requireNonNull(response.getBody()).getRedirectUrl())
                .build();
    }

    private LoanTransactionResponse convertToLoanTransactionResponse(LoanTransaction loanTransaction) {
        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .loanTypeId(loanTransaction.getLoanType().getId())
                .instalmentTypeId(loanTransaction.getInstalmentType().getId())
                .customerId(loanTransaction.getCustomer().getId())
                .nominal(loanTransaction.getNominal())
                .approvedAt(loanTransaction.getApprovedAt())
                .approvedBy(loanTransaction.getApprovedBy())
                .approvalsStatus(String.valueOf(loanTransaction.getApprovalStatus()))
                .transactionDetail(loanTransaction.getLoanTransactionDetails())
                .createAt(loanTransaction.getCreatedAt())
                .updatedAt(loanTransaction.getUpdatedAt())
                .build();
    }

//    private LoanTransactionDetailResponses convertToLoanTransactionDetailsResponse(LoanTransactionDetail loanTransactionDetail){
//        return LoanTransactionDetailResponses.builder()
//               .id(loanTransactionDetail.getId())
//               .loanStatus(loanTransactionDetail.getLoanStatus())
//               .transactionDate(loanTransactionDetail.getTransactionDate())
//               .nominal(Math.round(loanTransactionDetail.getNominal()))
//               .createdAt(loanTransactionDetail.getCreatedAt())
//               .updatedAt(loanTransactionDetail.getUpdatedAt())
//               .build();
//    }

    private LoanTransaction findByIdOrThrowNotFound(String id) {
        return loanTransactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
    private LoanTransactionDetail findByIdTrxDetailOrThrowNotFound(String id) {
        return loanTransactionDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
}
