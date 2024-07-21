package com.enigmacamp.loanApp.repository;

import com.enigmacamp.loanApp.model.entity.ImageGuarantee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageGuaranteeRepository extends JpaRepository<ImageGuarantee, String> {
}
