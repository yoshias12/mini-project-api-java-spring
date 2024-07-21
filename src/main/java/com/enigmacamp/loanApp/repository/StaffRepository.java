package com.enigmacamp.loanApp.repository;

import com.enigmacamp.loanApp.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
}
