package com.enigmacamp.loanApp.repository;

import com.enigmacamp.loanApp.model.entity.InstalmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String> {
}
