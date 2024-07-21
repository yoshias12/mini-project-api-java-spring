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
@Table(name = "t_loan_document")
public class LoanDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "content_type")
    private String contentType;
    private String name;
    private String path;
    private Long size;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    @JsonIgnore
    private Customer customerId;
}
