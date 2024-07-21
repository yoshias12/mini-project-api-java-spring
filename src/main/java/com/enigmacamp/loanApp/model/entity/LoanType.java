package com.enigmacamp.loanApp.model.entity;

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
@Table(name = "t_loan_type")
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;
    @Column(name = "maximum_loan")
    private Double maxLoan;
}
