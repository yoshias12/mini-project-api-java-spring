package com.enigmacamp.loanApp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trx_loan")
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "approval_status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus; // enum
    @Column(name = "approved_at")
    private Long approvedAt;
    @Column(name = "approved_by")
    private String approvedBy;
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "nominal")
    private Double nominal;
    @Column(name = "update_at")
    private Long updatedAt;
    @Column(name = "rejected_at")
    private Long rejectedAt;
    @Column(name = "rejected_by")
    private String rejectedBy;

    @ManyToOne
    @JoinColumn(name = "customer_id")

    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "instalment_type_id")

    private InstalmentType instalmentType;
    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    @OneToMany(mappedBy = "loanTransaction", cascade = CascadeType.ALL)
    private List<LoanTransactionDetail> loanTransactionDetails;

    public enum ApprovalStatus {
        APPROVED,
        REJECTED;
    }
}
