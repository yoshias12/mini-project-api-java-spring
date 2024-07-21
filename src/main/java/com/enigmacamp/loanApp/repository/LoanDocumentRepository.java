package com.enigmacamp.loanApp.repository;

import com.enigmacamp.loanApp.model.entity.LoanDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDocumentRepository extends JpaRepository<LoanDocument, String> {
}
