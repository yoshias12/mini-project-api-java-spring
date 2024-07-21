package com.enigmacamp.loanApp.repository;

import com.enigmacamp.loanApp.model.entity.ImageCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageCustomerRepository extends JpaRepository<ImageCustomer, String> {
}
