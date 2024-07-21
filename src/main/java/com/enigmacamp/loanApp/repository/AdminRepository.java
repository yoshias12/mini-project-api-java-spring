package com.enigmacamp.loanApp.repository;

import com.enigmacamp.loanApp.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
}
