package com.enigmacamp.loanApp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trx_loan_detail")
public class LoanTransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "transaction_Date")
    private Long transactionDate;
    private Double nominal;
    @Column(name = "loan_status")
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus; // enum
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "updated_at")
    private Long updatedAt;
    @ManyToOne
    @JoinColumn(name = "guarantee_picture_id")
    private ImageGuarantee guaranteePictureId;

    @ManyToOne
    @JoinColumn(name = "trx_loan_id")
    @JsonIgnore
    @JsonManagedReference
    private LoanTransaction loanTransaction;

    public enum LoanStatus {
        PAID,
        UNPAID
    }
}
