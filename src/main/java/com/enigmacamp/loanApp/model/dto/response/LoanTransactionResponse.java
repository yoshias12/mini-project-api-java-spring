package com.enigmacamp.loanApp.model.dto.response;

import com.enigmacamp.loanApp.model.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionResponse {
    private String id;
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerId;
    private Double nominal;
    private Long approvedAt;
    private String approvedBy;
    private String approvalsStatus;
    private List<LoanTransactionDetail> transactionDetail;
    private Long createAt;
    private Long updatedAt;
}
