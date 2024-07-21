package com.enigmacamp.loanApp.repository;

import com.enigmacamp.loanApp.model.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, String> {
}
